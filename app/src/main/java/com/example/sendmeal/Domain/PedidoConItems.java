package com.example.sendmeal.Domain;
import androidx.room.Entity;
import androidx.room.ForeignKey;


@Entity(tableName = "pedido_items_join",
        primaryKeys = { "item_id", "pedido_id" },
        foreignKeys = {
                @ForeignKey(entity = ItemPedido.class,
                        parentColumns = "id",
                        childColumns = "item_id",
                        onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Pedido.class,
                        parentColumns = "id",
                        childColumns = "pedido_id",
                        onDelete = ForeignKey.CASCADE)
        })
public class PedidoConItems


{
    public int item_id;
    public int pedido_id;









    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }



}
