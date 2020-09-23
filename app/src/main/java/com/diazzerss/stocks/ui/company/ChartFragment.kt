package com.diazzerss.stocks.ui.company

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.diazzerss.stocks.R
import com.diazzerss.stocks.databinding.FragmentChartBinding
import com.diazzerss.stocks.model.Graph
import com.diazzerss.stocks.utils.getViewModel
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.fragment_chart.*
import kotlinx.android.synthetic.main.view_marker.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ChartFragment : Fragment() {

    private val vm by lazy { getViewModel<ChartViewModel>() }
    private val ticker by lazy { arguments?.getString("ticker").toString() }

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
        bindViewModel()
        return FragmentChartBinding.inflate(inflater, container, false).root
    }

    private fun bindViewModel() {
        vm.getChartData(ticker)
        vm.graph.observe(viewLifecycleOwner, Observer {
            chartInit(it)
        })


    }

    private fun chartInit(graphList: ArrayList<Graph>) {

        val date = arrayListOf<String>()

        class MyXAxisFormatter : ValueFormatter() {

            val sdfIn = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ROOT).apply {
                timeZone = TimeZone.getTimeZone("GMT-4")
            }
            val sdfOut = SimpleDateFormat("d MMMM, HH:mm", Locale.forLanguageTag("RU")).apply {
                timeZone = TimeZone.getTimeZone("GMT+3")
            }

            override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                for (graph in graphList) {
                    date.add(sdfOut.format(sdfIn.parse(graph.date)))
                }
                date.reverse()
                return date.getOrNull(value.toInt()) ?: value.toString()
            }
        }

        class CustomMarkerView(context: Context, layoutResource: Int) :
            MarkerView(context, layoutResource) {

            override fun refreshContent(e: Entry, highlight: Highlight) {
                tv_marker_date.text =
                    getString(R.string.marker_concat_date, date[highlight.x.toInt()])
                tv_marker_open.text =
                    getString(
                        R.string.marker_concat_open,
                        graphList[highlight.x.toInt()].open.toString()
                    )
                tv_marker_close.text = getString(
                    R.string.marker_concat_close,
                    graphList[highlight.x.toInt()].close.toString()
                )
                tv_marker_low.text = getString(
                    R.string.marker_concat_low,
                    graphList[highlight.x.toInt()].low.toString()
                )
                tv_marker_high.text = getString(
                    R.string.marker_concat_high,
                    graphList[highlight.x.toInt()].high.toString()
                )
                tv_marker_volume.text = getString(
                    R.string.marker_concat_volume,
                    graphList[highlight.x.toInt()].volume.toString()
                )

                super.refreshContent(e, highlight)
            }

            private var mOffset: MPPointF? = null
            override fun getOffset(): MPPointF {
                if (mOffset == null) {
                    // center the marker horizontally and vertically
                    mOffset = MPPointF((-(width / 2)).toFloat(), (-height).toFloat());
                }
                return mOffset as MPPointF

            }

            override fun draw(canvas: Canvas?, posX: Float, posY: Float) {
                super.draw(canvas, posX, posY)
                getOffsetForDrawingAtPoint(posX, posY)
            }
        }

        val chart: LineChart = graphics_lineChart
        chart.apply {
            legend.isEnabled = false
            description.apply {
                text = "GMT+03:00"
                textSize = 10f
                xOffset = 4f
                yOffset = 4f
            }
            isAutoScaleMinMaxEnabled = true
            marker = CustomMarkerView(context, R.layout.view_marker).apply {
                chartView = chart
            }


            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textSize = 10f
                setDrawAxisLine(true)
                setDrawGridLines(false)
                valueFormatter = MyXAxisFormatter()
                setLabelCount(3, true)
                setAvoidFirstLastClipping(true)
            }

            axisLeft.apply {
                textSize = 10f
                setDrawGridLines(false)
                setDrawZeroLine(true)
                setLabelCount(9, true)
            }

            axisRight.isEnabled = false

        }
        val entries = ArrayList<Entry>()
        var entryIndex = 0f

        for (graph in graphList) {
            entries.add(Entry(entryIndex, graph.close.toFloat()))
            entryIndex++
        }

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


