package sakigake.mzaziconnect.mzaziconnectapplication.ui.teacher

import AssignAdapter
import AssignmentViewModel
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import sakigake.mzaziconnect.mzaziconnectapplication.R
import sakigake.mzaziconnect.mzaziconnectapplication.databinding.ActivitySubjectAssignmentBinding
import sakigake.mzaziconnect.mzaziconnectapplication.model.AssignmentData
import sakigake.mzaziconnect.mzaziconnectapplication.model.AssignmentsData
import sakigake.mzaziconnect.mzaziconnectapplication.model.TopicsData
import sakigake.mzaziconnect.mzaziconnectapplication.ui.parent.TopicsAdapter

class SubjectAssignmentActivity : AppCompatActivity() {
    lateinit var binding: ActivitySubjectAssignmentBinding
    val assignViewModel: AssignmentViewModel by viewModels()
    private val default_notification_channel_id = "default"
    private var previousAssignList = emptyList<AssignmentsData>()


    companion object {
        const val NOTIFICATION_CHANNEL_ID = "10001"
        private const val default_notification_channel_id = "default"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectAssignmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnadd.setOnClickListener {
            val intent = Intent(this, EditAssignmentActivity::class.java)
            startActivity(intent)
        }
        binding.ivhome.setOnClickListener {
            startActivity(Intent(this, NavActivity::class.java))
        }

        binding.imgBack.setOnClickListener {
            val intent = Intent(this, NavActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        fetchingAssign()

    }

    fun fetchingAssign() {
        assignViewModel.fetchAssign()
        assignViewModel.postsLiveData.observe(
            this,
            Observer { assignmentList ->
                val newAssignments = assignmentList.filter { assignment -> !previousAssignList.contains(assignment) }

                if (newAssignments.isNotEmpty()) {
                    createNotification(newAssignments)
                }

                previousAssignList = assignmentList

                val postsAdapter = AssignAdapter(assignmentList ?: emptyList(),this)
                binding.rvAssignmentTopics.layoutManager = LinearLayoutManager(this@SubjectAssignmentActivity)
                binding.rvAssignmentTopics.adapter = postsAdapter
            })

        assignViewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
        })
    }

    private fun createNotification(newAssignments: List<AssignmentsData>) {
        if (newAssignments.isNotEmpty()) {
            val mostRecentAssignment = newAssignments.first()

            val sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + packageName + "/raw/quite_impressed.mp3")
            val mBuilder = NotificationCompat.Builder(this@SubjectAssignmentActivity, "10001")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("MzaziConnect")
                .setSound(sound)
                .setContentText("New Assignment: ${mostRecentAssignment.topic}")

            val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//                mNotificationManager.notify(1,mBuilder)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()

                val importance = NotificationManager.IMPORTANCE_HIGH
                val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(true)
                notificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
                notificationChannel.setSound(sound, audioAttributes)
                mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
                mNotificationManager.createNotificationChannel(notificationChannel)
            }

            mNotificationManager.notify(mostRecentAssignment.subject.hashCode(), mBuilder.build())
        }
    }



}