package gumilar.unikom.formulirfgdapps

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import gumilar.unikom.formulirfgdapps.databinding.ActivityMainBinding
import gumilar.unikom.formulirfgdapps.model.Participant

// Hari/tanggal: Senin, 19 Mei 2025
// NIM        : 10122155
// Nama       : Gumilar Muhammad Naqib
// Kelas      : PA-3

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val categories = listOf(
        "Akademisi",
        "Bisnis",
        "Komunitas",
        "Pemerintah",
        "Media",
        "Organisasi",
        "NGO"
    )

    private val skillMap by lazy {
        listOf(
            Pair(binding.chkAlgo, "Algoritma"),
            Pair(binding.chkProgramming, "Pemrograman"),
            Pair(binding.chkDesignThinking, "Design Thinking"),
            Pair(binding.chkProblemSolving, "Problem Solving"),
            Pair(binding.chkCriticalThinking, "Critical Thinking"),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spnCategory.adapter = adapter


        binding.btnSubmit.setOnClickListener {
            val name = binding.edtName.text.toString()
            val phone = binding.edtPhone.text.toString()
            val email = binding.edtEmail.text.toString()
            val gender = when{
                binding.radMale.isChecked -> "Laki-laki"
                binding.radFemale.isChecked -> "Perempuan"
                else -> ""
            }

            val skillset = skillMap.filter { it.first.isChecked }.map { it.second }
            if (skillset.isEmpty()) {
                Toast.makeText(this, "Pilih minimal 1 skill", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val category = binding.spnCategory.selectedItem.toString()

            if (name.isEmpty() || phone.isEmpty() || email.isEmpty() || gender.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val participant = Participant(name, phone, email, gender, skillset, category)
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("participant", participant)
            startActivity(intent)
        }
    }
}