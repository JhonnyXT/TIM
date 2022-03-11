package com.example.tim

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class CustomAdapter(dbLocal: DetailDatabase, context: Context) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private var dblocal2:DetailDatabase=dbLocal
    private var context:Context=context

    val titles = arrayOf(
        "Nueva entrega",
        "Nueva entrega",
        "Nueva recogida",
        "Nueva recogida",
        "Nueva recogida",
        "Nueva recogida",
    )
    val numbers = arrayOf(
        12356,
       781,
        11111,
        44444,
        77777,
        33333,
    )

    val cities = arrayOf(
        "Envigado - Antioquia",
        "Envigado - Antioquia",
        "Medellin- Antioquia",
        "Medellin - Antioquia",
        "Envigado - Antioquia",
        "Envigado - Antioquia"
    )

    val parcels = arrayOf(
        "20028000992",
        "200280560993",
        "23090008048",
        "23090008046",
        "23090008045",
        "20028000991"
    )

    val units = arrayOf(
        "Unidades: 01",
        "Unidades: 02",
        "Unidades: 02",
        "Unidades: 02",
        "Unidades: 01",
        "Unidades: 02"
    )

    val images = intArrayOf(
        R.drawable.ic_baseline_moped_24,
        R.drawable.ic_baseline_moped_24,
        R.drawable.ic_baseline_airport_shuttle_24,
        R.drawable.ic_baseline_airport_shuttle_24,
        R.drawable.ic_baseline_airport_shuttle_24,
        R.drawable.ic_baseline_airport_shuttle_24
    )

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.itemTitle.text = titles[i]
        viewHolder.itemCity.text = cities[i]
        viewHolder.itemParcel.text = parcels[i]
        viewHolder.itemUnits.text = units[i]
        viewHolder.itemImage.setImageResource(images[i])
        viewHolder.cardView.setOnClickListener( View.OnClickListener {
            dblocal2.detailDao().insertDetail(DetailEntity(titles[i],cities[i],numbers[i]))
            Intent(
                context.applicationContext,
                DetailActivity::class.java
            )
        })

    }

    override fun getItemCount(): Int {
        return titles.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var itemTitle: TextView = itemView.findViewById(R.id.item_title)
        var itemCity: TextView = itemView.findViewById(R.id.item_city)
        var itemParcel: TextView = itemView.findViewById(R.id.item_parcel)
        var itemUnits: TextView = itemView.findViewById(R.id.item_units)
        var itemImage: ImageView = itemView.findViewById(R.id.item_image)
        var cardView: CardView = itemView.findViewById(R.id.card_view)

        init {
            itemView.setOnClickListener {
                itemView.context.startActivity(
                    Intent(
                        itemView.context,
                        DetailActivity::class.java
                    ).putExtra("titles", itemTitle.text).putExtra("parcels", itemParcel.text)
                )
            }
        }
    }
}
