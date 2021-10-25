package valenzuela.carlos.digimind.ui.dashboard

import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import valenzuela.carlos.digimind.R
import valenzuela.carlos.digimind.databinding.FragmentDashboardBinding
import valenzuela.carlos.digimind.ui.Task
import valenzuela.carlos.digimind.ui.home.HomeFragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val btn_time = root.findViewById<Button>(R.id.button_Time)
        btn_time.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                btn_time.text = SimpleDateFormat("HH:mm").format(calendar.time)
            }
            TimePickerDialog(root.context, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        val btnSave = root.findViewById<Button>(R.id.button_Save)

        btnSave.setOnClickListener {
            val taskTitle = root.findViewById<EditText>(R.id.taskTittle)
            val monday = root.findViewById<CheckBox>(R.id.monday)
            val tuesday = root.findViewById<CheckBox>(R.id.tuesday)
            val wednesday = root.findViewById<CheckBox>(R.id.wednesday)
            val thursday = root.findViewById<CheckBox>(R.id.thursday)
            val friday = root.findViewById<CheckBox>(R.id.friday)
            val saturday = root.findViewById<CheckBox>(R.id.saturday)
            val sunday = root.findViewById<CheckBox>(R.id.sunday)

            if (taskTitle.text.toString() != ""){

                val title: String = taskTitle.text.toString()
                var days = ArrayList<String>()

                if(monday.isChecked){
                    days.add("Monday")
                }
                if(tuesday.isChecked){
                    days.add("Tuesday")
                }
                if(wednesday.isChecked){
                    days.add("Wednesday")
                }
                if(thursday.isChecked){
                    days.add("Thursday")
                }
                if(friday.isChecked){
                    days.add("Friday")
                }
                if(saturday.isChecked){
                    days.add("Saturday")
                }
                if(sunday.isChecked){
                    days.add("Sunday")
                }

                if(days.isNotEmpty()){
                    if(btn_time.text.toString() != ""){
                        val time:String = btnSave.text.toString()

                        val task = Task(title, days, time)
                        HomeFragment.tasks.add(task)
                        Toast.makeText(root.context, "Task Saved", Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(root.context, "Please fill all sections", Toast.LENGTH_LONG).show()
                    }
                }else{
                    Toast.makeText(root.context, "Please fill all sections", Toast.LENGTH_LONG).show()
                }

            }else{
                Toast.makeText(root.context, "Please fill all sections", Toast.LENGTH_LONG).show()
            }




        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

private fun Intent.putExtra(s: String, task: Task) {

}
