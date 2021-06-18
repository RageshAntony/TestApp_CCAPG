/*     */ package mumbai.dev.sdkdubai.adapter;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.TextView;
/*     */ import androidx.recyclerview.widget.RecyclerView;
/*     */ import java.util.List;
/*     */ import mumbai.dev.sdkdubai.NewCreditCard;
/*     */ import mumbai.dev.sdkdubai.NewDebitCard;
/*     */ import mumbai.dev.sdkdubai.R;
/*     */ import mumbai.dev.sdkdubai.SavedCard;
/*     */ import mumbai.dev.sdkdubai.dto.PaymentType;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MovieAdapter
/*     */   extends RecyclerView.Adapter<MovieAdapter.ViewHolder>
/*     */ {
/*     */   private Context mContext;
/*     */   private List<PaymentType> mResultList;
/*     */   private String mresp;
/*     */   public TextView name;
/*     */   public ImageView rect;
/*     */   
/*     */   public MovieAdapter(Context context, List<PaymentType> resultList, String resp) {
/*  36 */     this.mContext = context;
/*  37 */     this.mResultList = resultList;
/*  38 */     this.mresp = resp;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
/*  46 */     View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_payment_option, viewGroup, false);
/*     */     
/*  48 */     return new ViewHolder(itemLayoutView);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onBindViewHolder(ViewHolder viewHolder, final int i) {
/*  54 */     this.name.setText("" + ((PaymentType)this.mResultList.get(i)).getName());
/*     */ 
/*     */     
/*  57 */     if (((PaymentType)this.mResultList.get(i)).getName().equalsIgnoreCase("Saved Card")) {
/*  58 */       this.rect.setImageResource(R.drawable.ic_saved_card);
/*  59 */     } else if (((PaymentType)this.mResultList.get(i)).getName().equalsIgnoreCase("Credit Card")) {
/*  60 */       this.rect.setImageResource(R.drawable.ic_credit_card);
/*  61 */     } else if (this.name.getText().toString().equalsIgnoreCase("Debit Card")) {
/*  62 */       this.rect.setImageResource(R.drawable.ic_debit_card);
/*  63 */     } else if (this.name.getText().toString().equalsIgnoreCase("Net Banking")) {
/*  64 */       this.rect.setImageResource(R.drawable.ic_net_banking);
/*  65 */     } else if (this.name.getText().toString().equalsIgnoreCase("Wallet")) {
/*  66 */       this.rect.setImageResource(R.drawable.ic_wallet);
/*     */     }
/*  68 */     else if (this.name.getText().toString().equalsIgnoreCase("Cash card")) {
/*  69 */       this.rect.setImageResource(R.drawable.ic_cash_card);
/*  70 */     } else if (this.name.getText().toString().equalsIgnoreCase("Mobile Payments")) {
/*  71 */       this.rect.setImageResource(R.drawable.ic_credit_card);
/*  72 */     } else if (this.name.getText().toString().equalsIgnoreCase("Unified Payments")) {
/*  73 */       this.rect.setImageResource(R.drawable.ic_mobile_upi);
/*     */     } 
/*     */     
/*  76 */     this.name.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onClick(View v)
/*     */           {
/* 101 */             JSONObject j = null;
/*     */             try {
/* 103 */               j = new JSONObject(MovieAdapter.this.mresp);
/*     */               
/* 105 */               if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Saved Card"))
/* 106 */               { MovieAdapter.this.callToDetails(((PaymentType)MovieAdapter.this.mResultList.get(i)).getName()); }
/* 107 */               else if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Credit Card"))
/* 108 */               { Intent i = new Intent(MovieAdapter.this.mContext, NewCreditCard.class);
/* 109 */                 i.putExtra("Payment_Type", j.getString("payOptions"));
/* 110 */                 MovieAdapter.this.mContext.startActivity(i); }
/* 111 */               else if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Debit Card"))
/*     */               
/*     */               { 
/* 114 */                 Intent i = new Intent(MovieAdapter.this.mContext, NewDebitCard.class);
/*     */                 
/* 116 */                 i.putExtra("Payment_Type", j.getString("payOptions"));
/* 117 */                 MovieAdapter.this.mContext.startActivity(i);
/*     */                  }
/*     */               
/* 120 */               else if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Net Banking"))
/* 121 */               { MovieAdapter.this.rect.setImageResource(R.drawable.ic_net_banking); }
/* 122 */               else if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Wallet"))
/* 123 */               { MovieAdapter.this.rect.setImageResource(R.drawable.ic_wallet); }
/*     */               
/* 125 */               else if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Cash card"))
/* 126 */               { MovieAdapter.this.rect.setImageResource(R.drawable.ic_cash_card); }
/* 127 */               else if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Mobile Payments"))
/* 128 */               { MovieAdapter.this.rect.setImageResource(R.drawable.ic_credit_card); }
/* 129 */               else if (((PaymentType)MovieAdapter.this.mResultList.get(i)).getName().equalsIgnoreCase("Unified Payments"))
/* 130 */               { MovieAdapter.this.rect.setImageResource(R.drawable.ic_mobile_upi); } 
/* 131 */             } catch (JSONException e) {
/* 132 */               e.printStackTrace();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callToDetails(String movie) {
/* 148 */     Intent i = new Intent(this.mContext, SavedCard.class);
/* 149 */     i.setFlags(268435456);
/*     */     
/*     */     try {
/* 152 */       JSONObject j = new JSONObject(this.mresp);
/* 153 */       i.putExtra("Payment_Type", j.getString("CustPayments"));
/* 154 */       this.mContext.startActivity(i);
/* 155 */     } catch (JSONException e) {
/* 156 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemCount() {
/* 163 */     return this.mResultList.size();
/*     */   }
/*     */   
/*     */   public class ViewHolder
/*     */     extends RecyclerView.ViewHolder
/*     */   {
/*     */     public ViewHolder(View itemView) {
/* 170 */       super(itemView);
/* 171 */       MovieAdapter.this.rect = (ImageView)itemView.findViewById(R.id.rect);
/* 172 */       MovieAdapter.this.name = (TextView)itemView.findViewById(R.id.name);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/adapter/MovieAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */