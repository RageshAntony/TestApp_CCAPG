/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.text.Editable;
/*     */ import android.text.TextWatcher;
/*     */ import android.view.LayoutInflater;
/*     */ import android.view.View;
/*     */ import android.view.ViewGroup;
/*     */ import android.widget.EditText;
/*     */ import android.widget.ImageView;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.TextView;
/*     */ import androidx.viewpager.widget.PagerAdapter;
/*     */ import androidx.viewpager.widget.ViewPager;
/*     */ import java.util.List;
/*     */ import mumbai.dev.sdkdubai.dto.SaveCard;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewPagerAdapter
/*     */   extends PagerAdapter
/*     */ {
/*     */   Context context;
/*     */   List<SaveCard> sc;
/*     */   EditText cvv;
/*     */   LayoutInflater inflater;
/*     */   TextView card_type;
/*     */   int pos;
/*     */   LinearLayout card_main;
/*     */   LinearLayout card_main2;
/*     */   private OnEditTextChanged onEditTextChanged;
/*     */   
/*     */   public ViewPagerAdapter(Context context, List<SaveCard> sc, OnEditTextChanged onEditTextChanged) {
/*  40 */     this.context = context;
/*  41 */     this.sc = sc;
/*  42 */     this.onEditTextChanged = onEditTextChanged;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCount() {
/*  48 */     return this.sc.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isViewFromObject(View view, Object object) {
/*  53 */     return (view == (RelativeLayout)object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object instantiateItem(ViewGroup container, int position) {
/*  61 */     this.pos = position;
/*     */     
/*  63 */     this
/*  64 */       .inflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
/*  65 */     View itemView = this.inflater.inflate(R.layout.viewpager_item, container, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  70 */     ImageView imageView = (ImageView)itemView.findViewById(R.id.imageView);
/*  71 */     this.card_type = (TextView)itemView.findViewById(R.id.card_type);
/*  72 */     TextView txt1 = (TextView)itemView.findViewById(R.id.txt1);
/*  73 */     TextView txt2 = (TextView)itemView.findViewById(R.id.txt2);
/*  74 */     TextView txt3 = (TextView)itemView.findViewById(R.id.txt3);
/*  75 */     TextView txt4 = (TextView)itemView.findViewById(R.id.txt4);
/*     */     
/*  77 */     this.card_main = (LinearLayout)itemView.findViewById(R.id.card_main);
/*  78 */     this.card_main2 = (LinearLayout)itemView.findViewById(R.id.card_main2);
/*  79 */     this.cvv = (EditText)itemView.findViewById(R.id.cvv);
/*  80 */     this.cvv.setText(((SaveCard)this.sc.get(position)).getCvv());
/*     */     
/*  82 */     if (PaymentOptions.pos_page == position) {
/*     */       
/*  84 */       this.card_main.setVisibility(0);
/*  85 */       this.card_main.setBackgroundResource(R.drawable.card);
/*     */     }
/*     */     else {
/*     */       
/*  89 */       this.card_main2.setVisibility(0);
/*  90 */       this.card_main.setBackgroundResource(R.drawable.card_swipe);
/*     */     } 
/*     */ 
/*     */     
/*  94 */     this.cvv.addTextChangedListener(new TextWatcher()
/*     */         {
/*     */           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
/* 102 */             ViewPagerAdapter.this.onEditTextChanged.onTextChanged(charSequence.toString());
/*     */           }
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
/*     */           public void afterTextChanged(Editable editable) {}
/*     */         });
/* 118 */     if (((SaveCard)this.sc.get(position)).getPayCardType().equalsIgnoreCase("CRDC")) {
/* 119 */       this.card_type.setText("CREDIT CARD");
/*     */     } else {
/* 121 */       this.card_type.setText("DEBIT CARD");
/* 122 */     }  if (((SaveCard)this.sc.get(position)).getPayCardName().contains("Visa") || ((SaveCard)this.sc.get(position)).getPayCardName().contains("VISA")) {
/* 123 */       imageView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.visa));
/* 124 */     } else if (((SaveCard)this.sc.get(position)).getPayCardName().contains("MasterCard")) {
/* 125 */       imageView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.master));
/* 126 */     } else if (((SaveCard)this.sc.get(position)).getPayCardName().contains("Amex")) {
/* 127 */       imageView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.amex));
/* 128 */     } else if (((SaveCard)this.sc.get(position)).getPayCardName().contains("Discover")) {
/* 129 */       imageView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.discover));
/* 130 */     } else if (((SaveCard)this.sc.get(position)).getPayCardName().contains("Diners")) {
/* 131 */       imageView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.dinersclub));
/* 132 */     } else if (((SaveCard)this.sc.get(position)).getPayCardName().contains("JCB")) {
/* 133 */       imageView.setImageDrawable(this.context.getResources().getDrawable(R.drawable.jcb));
/*     */     } 
/*     */ 
/*     */     
/* 137 */     txt1.setText(((SaveCard)this.sc.get(position)).getPayCardNo().charAt(0) + "");
/* 138 */     txt2.setText(((SaveCard)this.sc.get(position)).getPayCardNo().charAt(1) + "");
/* 139 */     txt3.setText(((SaveCard)this.sc.get(position)).getPayCardNo().charAt(2) + "");
/* 140 */     txt4.setText(((SaveCard)this.sc.get(position)).getPayCardNo().charAt(3) + "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 150 */     ((ViewPager)container).addView(itemView);
/*     */     
/* 152 */     return itemView;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyItem(ViewGroup container, int position, Object object) {
/* 158 */     ((ViewPager)container).removeView((View)object);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemPosition(Object object) {
/* 165 */     return -2;
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/ViewPagerAdapter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */