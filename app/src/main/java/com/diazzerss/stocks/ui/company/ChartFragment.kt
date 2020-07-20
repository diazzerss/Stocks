package com.diazzerss.stocks.ui.company

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diazzerss.stocks.databinding.FragmentChartBinding
import com.diazzerss.stocks.model.Graph
import com.diazzerss.stocks.utils.getViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.fragment_chart.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ChartFragment : Fragment() {

    private val vm by lazy { getViewModel<ChartViewModel>() }

    companion object {
        fun newInstance(ticker: String) = ChartFragment().apply {
            arguments = Bundle().apply {
                putString("ticker", ticker)
            }

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewModel()
        return FragmentChartBinding.inflate(inflater, container, false).root
    }

    private fun initViewModel() {
        vm.getChartData("AAPL")
        vm.graphicsLiveData.observe(viewLifecycleOwner, Observer {
            chartInit(it)
        })


    }

    private fun chartInit(graphList: ArrayList<Graph>) {

        class MyXAxisFormatter : ValueFormatter() {

            val sdfIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT)
            val sdfOut = SimpleDateFormat("HH:mm", Locale.ROOT).apply {
                timeZone.rawOffset = 25200000
            }
            val hours = arrayListOf<String>()

            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                for (graph in graphList) {
                    hours.add(sdfOut.format(sdfIn.parse(graph.date)))
                }
                hours.reverse()
                return hours.getOrNull(value.toInt()) ?: value.toString()
            }
        }

        val chart: LineChart = graphics_lineChart
        chart.apply {
            legend.isEnabled = false
            description.isEnabled = false
            isAutoScaleMinMaxEnabled = true

            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 12f
                setDrawAxisLine(true)
                setDrawGridLines(false)
                valueFormatter = MyXAxisFormatter()
            }

            axisLeft.apply {
                textSize = 12f
                setDrawGridLines(false)
                setDrawZeroLine(true)
            }

            axisRight.isEnabled = false

        }
        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, graphList[6].price))
        entries.add(Entry(1f, graphList[5].price))
        entries.add(Entry(2f, graphList[4].price))
        entries.add(Entry(3f, graphList[3].price))
        entries.add(Entry(4f, graphList[2].price))
        entries.add(Entry(5f, graphList[1].price))
        entries.add(Entry(6f, graphList[0].price))


        val dataSet = LineDataSet(entries, "Label").apply {

            mode = LineDataSet.Mode.CUBIC_BEZIER;
            setDrawFilled(true)
            fillColor = Color.parseColor("#2196F3")
            setDrawCircleHole(false)
            setCircleColor(Color.parseColor("#1565C0"))
            color = Color.parseColor("#2196F3")

        }

        val lineData = LineData(dataSet)
        lineData.setDrawValues(false)

        chart.data = lineData
        chart.invalidate()
    }

}
