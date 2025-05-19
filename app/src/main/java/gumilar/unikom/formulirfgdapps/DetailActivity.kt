package gumilar.unikom.formulirfgdapps

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import gumilar.unikom.formulirfgdapps.databinding.ActivityDetailBinding
import gumilar.unikom.formulirfgdapps.model.Participant

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val participant = intent.getParcelableExtra<Participant>("participant")
        if (participant != null) {
            binding.txtNamaResult.text = participant.name
            binding.txtPhoneResult.text = participant.phone
            binding.txtEmailResult.text = participant.email
            binding.txtGenderResult.text = participant.gender
            binding.txtCategoryResult.text = participant.category
            binding.txtSkillsetResult.text = participant.skillset.joinToString(", ")
        }

        binding.btnInfoDeveloper.setOnClickListener {
            val bottomSheet = InfoDeveloper()
            bottomSheet.show(supportFragmentManager, "InfoDeveloper")
        }
    }
}