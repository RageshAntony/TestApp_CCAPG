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
/*    */ import mumbai.dev.sdkdubai.dto.CardTypeDTO;
/*    */ 
/*    */ public class CardAdapter
/*    */   extends ArrayAdapter<CardTypeDTO>
/*    */ {
/*    */   private Activity context;
/* 18 */   ArrayList<CardTypeDTO> data = null;
/*    */ 
/*    */   
/*    */   public CardAdapter(Activity context, int resource, ArrayList<CardTypeDTO> data) {
/* 22 */     super((Context)context, resource, data);
/* 23 */     this.context = context;
/* 24 */     this.data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public View getView(int position, View convertView, ViewGroup parent) {
/* 29 */     super.getView(position, convertView, parent);
/* 30 */     return getDropDownView(position, convertView, parent);
/*    */   }
/*    */ 
/*    */   
/*    */   public View getDropDownView(int position, View convertView, ViewGroup parent) {
/* 35 */     View row = convertView;
/* 36 */     if (row == null) {
/* 37 */       LayoutInflater inflater = this.context.getLayoutInflater();
/* 38 */       row = inflater.inflate(R.layout.spinner_item, parent, false);
/*    */     } 
/* 40 */     CardTypeDTO cardType = this.data.get(position);
/* 41 */     if (cardType != null) {
/* 42 */       TextView cardName = (TextView)row.findViewById(R.id.item_value);
/* 43 */       cardName.setText(cardType.getCardName());
/*    */     } 
/* 45 */     return row;
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/adapter/CardAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */