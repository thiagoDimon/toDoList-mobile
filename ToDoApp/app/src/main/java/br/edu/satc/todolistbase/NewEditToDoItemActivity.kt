package br.edu.satc.todolistbase

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import br.edu.satc.todolistbase.roomdatabase.AppDatabase
import br.edu.satc.todolistbase.roomdatabase.ToDoItem

class NewEditToDoItemActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var etTitle: EditText
    private lateinit var etDescription: EditText
    private lateinit var btButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_edit_to_do_item)

        initDatabase()

        etTitle = findViewById(R.id.et_title)
        etDescription = findViewById(R.id.et_description)
        btButton = findViewById(R.id.bt_save_item)

        btButton.setOnClickListener{
            save()
        }
    }

    private fun save(){
        // seta valores na variavel
        val toDoItem = ToDoItem(null, etTitle.text.toString(), etDescription.text.toString(), false)

        // chama controlador para inserir no banco
        db.toDoItemDao().insertAll(toDoItem)

        // menssagem popup
        Toast.makeText(this, "Salvo!", Toast.LENGTH_SHORT).show()

        // herda de activy esse metodo pra fechar
        finish()
    }

    private fun initDatabase() {
        // Inicializar nosso banco de dados Android Room Persistence com SQLITE
        //teste
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-todolist"
        )
            .allowMainThreadQueries()
            .build()
    }
}