/*    */ package mumbai.dev.sdkdubai.adapter;
/*    */ 
/*    */ import android.app.Activity;
/*    */ import android.content.Context;
/*    */ import android.view.LayoutInflater;
/*    */ import android.view.View;
/*    */ import android.view.ViewGroup;
/*    */ import android.widget.ArrayAdapter;
/*    */ import android.widget.TextView;
/*    */ import java.util.ArrayList;
/*    */ import mumbai.dev.sdkdubai.R;
/*    */ 
/*    */ public class CardNameAdapter
/*    */   extends ArrayAdapter<String>
/*    */ {
/*    */   private Activity context;
/* 17 */   ArrayList<String> data = null;
/*    */ 
/*    */   
/*    */   public CardNameAdapter(Activity context, int resource, ArrayList<String> data) {
/* 21 */     super((Context)context, resource, data);
/* 22 */     this.context = context;
/* 23 */     this.data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public View getView(int position, View convertView, ViewGroup parent) {
/* 28 */     super.getView(position, convertView, parent);
/* 29 */     return getDropDownView(position, convertView, parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public View getDropDownView(int position, View convertView, ViewGroup parent) {
/* 34 */     View row = convertView;
/* 35 */     if (row == null) {
/* 36 */       LayoutInflater inflater = this.context.getLayoutInflater();
/* 37 */       row = inflater.inflate(R.layout.spinner_item, parent, false);
/*    */     } 
/* 39 */     String cardName = this.data.get(position);
/* 40 */     if (cardName != null) {
/* 41 */       TextView txtCardName = (TextView)row.findViewById(R.id.item_value);
/* 42 */       txtCardName.setText(cardName);
/*    */     } 
/* 44 */     return row;
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/adapter/CardNameAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */