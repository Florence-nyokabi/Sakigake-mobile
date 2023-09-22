package sakigake.mzaziconnect.mzaziconnectapplication.ui

import SubjectAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.squareup.picasso.Picasso
import sakigake.mzaziconnect.mzaziconnectapplication.database.Subjects
import sakigake.mzaziconnect.mzaziconnectapplication.databinding.ActivityChildGradeBinding



class ChildGrade: AppCompatActivity () {
    lateinit var binding: ActivityChildGradeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildGradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val subjects = listOf(
            Subjects("English","https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151509/Frame_180_1_kjclpx.jpg" ,"Ms Joan Njiru" ),
            Subjects("Math","https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151508/Frame_181_rfkkvo.jpg" ,"Ms Florence"),
            Subjects("Science", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151508/Frame_186_1_ldcvij.jpg","Mr Pepeo"),
            Subjects("Agriculture", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151508/Frame_181_rfkkvo.jpg","Mr Kimamo"),
            Subjects("Kiswahili","https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151509/Frame_182_hgbhpy.jpg", "Ms Nakakande"),
            Subjects("Art", "https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151508/Frame_183_1_pdflt4.jpg", "Ms Kevine"),
            Subjects("English","https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151509/Frame_180_1_kjclpx.jpg" ,"Ms Joan Njiru" ),
            Subjects("Math","https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151508/Frame_181_rfkkvo.jpg" ,"Ms Florence"),
            Subjects("English","https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151509/Frame_180_1_kjclpx.jpg" ,"Ms Joan Njiru" ),
            Subjects("Math","https://res.cloudinary.com/dyxt6pqtx/image/upload/v1695151508/Frame_181_rfkkvo.jpg" ,"Ms Florence"),
            )

        val subjectAdapter = SubjectAdapter(subjects) { selectedSubject ->
            val intent = Intent(this, SubjectAssignmentsActitvity::class.java)
            intent.putExtra("TopicName", selectedSubject.subjectName)
            intent.putExtra("AssignmentDetails", selectedSubject.subjectTeacherName)
            intent.putExtra("DueDate", selectedSubject.subjectImageUrl)
            startActivity(intent)
        }
        binding.RvSubjectCards.adapter = subjectAdapter
        val layoutManager = GridLayoutManager(this, 2)
        binding.RvSubjectCards.layoutManager = layoutManager
    }

    override fun onResume() {
        super.onResume()
        binding.imgBack.setOnClickListener {
            val intent = Intent(this, ViewChild::class.java)
            startActivity(intent)
        }
        binding.btnViewAssignments.setOnClickListener {
            val intent = Intent(this, SubjectChoosenAssignments::class.java)
            startActivity(intent)
        }
    }

}