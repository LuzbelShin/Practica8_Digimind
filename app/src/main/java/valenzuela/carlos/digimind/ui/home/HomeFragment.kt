package valenzuela.carlos.digimind.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import valenzuela.carlos.digimind.R
import valenzuela.carlos.digimind.databinding.FragmentHomeBinding
import valenzuela.carlos.digimind.ui.Task

class HomeFragment : Fragment() {

    private var adapter:AdaptadorTareas? = null
    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object{
        var tasks = ArrayList<Task>()
        var firstTime = true
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if(firstTime){
            fillTasks()
            firstTime = false
        }

        adapter = AdaptadorTareas(root.context, tasks)

        val gridView: GridView = root.findViewById(R.id.gridView)
        gridView.adapter = adapter

        return root
    }

    fun fillTasks(){
        tasks.add(Task("Practice 1", arrayListOf("Tuesday"), "17:30"))
        tasks.add(Task("Practice 2", arrayListOf("Monday", "Sunday"), "17:40"))
        tasks.add(Task("Practice 3", arrayListOf("Wednesday"), "14:00"))
        tasks.add(Task("Practice 4", arrayListOf("Saturday"), "11:00"))
        tasks.add(Task("Practice 5", arrayListOf("Friday"), "13:00"))
        tasks.add(Task("Practice 6", arrayListOf("Thursday"), "10:40"))
        tasks.add(Task("Practice 7", arrayListOf("Monday"), "12:00"))

    }

    private class AdaptadorTareas:BaseAdapter{
        var tasks = ArrayList<Task>()
        var context: Context?= null


        constructor(context: Context, tasks: ArrayList<Task>){
            this.context = context
            this.tasks =tasks
        }
        override fun getCount(): Int {
            return tasks.size
        }

        override fun getItem(position: Int): Any {
            return tasks[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val task = tasks[position]
            val infator = LayoutInflater.from(context)
            val view = infator.inflate(R.layout.task_view, null)

            val tvTitle = view.findViewById<TextView>(R.id.tv_tittle)
            val tvTime = view.findViewById<TextView>(R.id.tv_time)
            val tvDays = view.findViewById<TextView>(R.id.tv_days)

            tvTitle.text = task.tittle
            tvTime.text = task.time
            tvDays.text = task.days.toString()

            return view
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}