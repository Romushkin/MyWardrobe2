package com.example.test

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.test.databinding.ActivityDetailBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var cloth: Cloth
    private var articles = Clothes.cloths

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.hasExtra("article")) {
            cloth = intent.getSerializableExtra("article") as Cloth
        }

        binding.backBTN.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.detailActivityImageViewIV.setImageResource(cloth.image)
        binding.detailActivityNameTextViewTV.text = cloth.name
        binding.detailActivitysDescriptionTextViewTV.text = cloth.description


        binding.detailActivityConstraintLayout.setOnLongClickListener {
            val dialog = AlertDialog.Builder(this)
            val inflater = this.layoutInflater
            val dialogView = inflater.inflate(R.layout.update_dialog, null)
            dialog.setView(dialogView)
            val editName: EditText = dialogView.findViewById(R.id.updateDialogNameET)
            val editDescription: EditText = dialogView.findViewById(R.id.updateDialogDescriptionET)

            dialog.setTitle("Обновить запись")
            dialog.setMessage("Введите данные ниже")
            dialog.setPositiveButton("Обновить") { _, _ ->
                binding.detailActivityNameTextViewTV.text = editName.text.toString()
                binding.detailActivitysDescriptionTextViewTV.text = editDescription.text.toString()
                val newName = binding.detailActivityNameTextViewTV.text.toString()
                val newDescription = binding.detailActivitysDescriptionTextViewTV.text.toString()
                val newCloth = Cloth(cloth.id, newName, newDescription, cloth.image)

                val index = search(articles, cloth)
                swap(articles, index, newCloth)

                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("index", index)
                startActivity(intent)

            }
            dialog.setNegativeButton("Отмена") { _, _ -> }
            dialog.create().show()
            false
        }

    }

    private fun swap(cloths: MutableList<Cloth>, index: Int, newCloth: Cloth) {
        cloths.add(index + 1, newCloth)
        cloths.removeAt(index)
    }

    private fun search(cloths: MutableList<Cloth>, oldCloth: Cloth): Int {
        var result = -1
        for (i in cloths.indices) {
            if (oldCloth.id == cloths[i].id) result = i
        }
        return result
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.exitMenu) finishAffinity()
        return super.onOptionsItemSelected(item)
    }

}