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
/*    */ import mumbai.dev.sdkdubai.dto.EMIOptionDTO;
/*    */ 
/*    */ public class EMIAdapter
/*    */   extends ArrayAdapter<EMIOptionDTO> {
/*    */   private Activity context;
/* 17 */   ArrayList<EMIOptionDTO> data = null;
/*    */ 
/*    */   
/*    */   public EMIAdapter(Activity context, int resource, ArrayList<EMIOptionDTO> data) {
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
/* 39 */     EMIOptionDTO emiOptionDTO = this.data.get(position);
/* 40 */     if (emiOptionDTO != null) {
/* 41 */       TextView gtwName = (TextView)row.findViewById(R.id.item_value);
/* 42 */       gtwName.setText(emiOptionDTO.getGtwName());
/*    */     } 
/* 44 */     return row;
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/adapter/EMIAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */