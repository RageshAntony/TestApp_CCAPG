/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.content.ClipData;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.util.Log;
/*     */ import android.view.View;
/*     */ import android.widget.Button;
/*     */ import android.widget.TextView;
/*     */ import android.widget.Toast;
/*     */ import androidx.appcompat.app.AppCompatActivity;
/*     */ import androidx.viewpager.widget.PagerAdapter;
/*     */ import androidx.viewpager.widget.ViewPager;
/*     */ import belka.us.androidtoggleswitch.widgets.BaseToggleSwitch;
/*     */ import belka.us.androidtoggleswitch.widgets.ToggleSwitch;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import mumbai.dev.sdkdubai.dto.SaveCard;
/*     */ import net.cachapa.expandablelayout.ExpandableLayout;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ public class SavedCard
/*     */   extends AppCompatActivity
/*     */   implements View.OnClickListener
/*     */ {
/*     */   ViewPager viewPager;
/*     */   PagerAdapter adapter;
/*     */   private ExpandableLayout expandableLayout0;
/*     */   TextView expand;
/*     */   TextView up;
/*     */   Button pay_card;
/*     */   Context mContext;
/*     */   TextView tv_inr;
/*     */   TextView tv_orderid;
/*  38 */   List<SaveCard> SavedCardList = new ArrayList<>();
/*     */   
/*     */   TextView payother;
/*     */   MerchantDetails merchant;
/*     */   BillingAddress billing;
/*     */   ShippingAddress shipping;
/*     */   ToggleSwitch toggleSwitch;
/*     */   JSONArray vJsonArr;
/*  46 */   ArrayList<String> cvv_list = new ArrayList<>();
/*     */   private ArrayList<ClipData.Item> items;
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/*  50 */     super.onCreate(savedInstanceState);
/*  51 */     setContentView(R.layout.activity_saved_card);
/*     */     
/*  53 */     this.mContext = (Context)this;
/*  54 */     this.toggleSwitch = (ToggleSwitch)findViewById(R.id.toggleswitch);
/*  55 */     this.expandableLayout0 = (ExpandableLayout)findViewById(R.id.expandable_layout_01);
/*  56 */     this.payother = (TextView)findViewById(R.id.payother);
/*  57 */     this.pay_card = (Button)findViewById(R.id.pay_card);
/*  58 */     this.expand = (TextView)findViewById(R.id.expand);
/*  59 */     this.up = (TextView)findViewById(R.id.up);
/*     */     
/*  61 */     this.merchant = PaymentOptions.merchant;
/*  62 */     this.billing = PaymentOptions.billing;
/*  63 */     this.shipping = PaymentOptions.shipping;
/*     */     
/*  65 */     this.tv_inr = (TextView)findViewById(R.id.tv_inr);
/*  66 */     this.tv_orderid = (TextView)findViewById(R.id.tv_orderid);
/*  67 */     this.tv_inr.setText(this.merchant.currency + " " + this.merchant.amount);
/*  68 */     this.tv_orderid.setText("Order #: " + this.merchant.order_id);
/*  69 */     this.payother.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View v) {
/*  72 */             Intent i = new Intent((Context)SavedCard.this, PaymentOptions.class);
/*     */             
/*  74 */             i.putExtra("merchant", PaymentOptions.merchant);
/*  75 */             i.putExtra("billing", PaymentOptions.billing);
/*  76 */             i.putExtra("shipping", PaymentOptions.shipping);
/*  77 */             SavedCard.this.startActivity(i);
/*     */           }
/*     */         });
/*     */     try {
/*  81 */       this.vJsonArr = new JSONArray(getIntent().getStringExtra("Payment_Type"));
/*     */       
/*  83 */       for (int i = 0; i < this.vJsonArr.length(); i++)
/*     */       {
/*  85 */         JSONObject j = this.vJsonArr.getJSONObject(i);
/*  86 */         if (j.getString("payCardType").equalsIgnoreCase("CRDC")) {
/*  87 */           SaveCard sc = new SaveCard();
/*  88 */           sc.setPayOptId(j.getString("payOptId"));
/*  89 */           sc.setPayOption(j.getString("payOption"));
/*  90 */           sc.setPayCardType(j.getString("payCardType"));
/*  91 */           sc.setPayCardName(j.getString("payCardName"));
/*  92 */           sc.setPayCardNo(j.getString("payCardNo"));
/*  93 */           this.cvv_list.add("");
/*  94 */           this.SavedCardList.add(sc);
/*     */         
/*     */         }
/*     */       
/*     */       }
/*     */     
/*     */     }
/* 101 */     catch (JSONException e) {
/* 102 */       e.printStackTrace();
/*     */     } 
/*     */ 
/*     */     
/* 106 */     this.pay_card.setText("PAY " + PaymentOptions.merchant.currency + " " + PaymentOptions.merchant.amount);
/* 107 */     this.expand.setOnClickListener(this);
/* 108 */     this.up.setOnClickListener(this);
/* 109 */     this.pay_card.setOnClickListener(this);
/*     */ 
/*     */     
/* 112 */     this.expandableLayout0.setOnExpansionUpdateListener(new ExpandableLayout.OnExpansionUpdateListener()
/*     */         {
/*     */           public void onExpansionUpdate(float expansionFraction, int state) {
/* 115 */             Log.d("ExpandableLayout0", "State: " + state);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 120 */     this.viewPager = (ViewPager)findViewById(R.id.pager);
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.viewPager.setClipToPadding(false);
/*     */     
/* 126 */     this.viewPager.setPadding(60, 0, 60, 0);
/*     */     
/* 128 */     this.viewPager.setPageMargin(10);
/*     */     
/* 130 */     this.adapter = new ViewPagerAdapter((Context)this, this.SavedCardList, new OnEditTextChanged()
/*     */         {
/*     */           public void onTextChanged(String charSeq)
/*     */           {
/* 134 */             SavedCard.this.updateTotalValue(charSeq);
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 140 */     this.viewPager.setAdapter(this.adapter);
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
/* 152 */     this.toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener()
/*     */         {
/*     */ 
/*     */ 
/*     */           
/*     */           public void onToggleSwitchChangeListener(int position, boolean isChecked)
/*     */           {
/* 159 */             if (position == 0) {
/* 160 */               SavedCard.this.SavedCardList.clear();
/* 161 */               SavedCard.this.cvv_list.clear();
/*     */               
/*     */               try {
/* 164 */                 for (int i = 0; i < SavedCard.this.vJsonArr.length(); i++) {
/*     */                   
/* 166 */                   JSONObject j = SavedCard.this.vJsonArr.getJSONObject(i);
/* 167 */                   if (j.getString("payCardType").equalsIgnoreCase("CRDC")) {
/* 168 */                     SaveCard sc = new SaveCard();
/* 169 */                     sc.setPayOptId(j.getString("payOptId"));
/* 170 */                     sc.setPayOption(j.getString("payOption"));
/* 171 */                     sc.setPayCardType(j.getString("payCardType"));
/* 172 */                     sc.setPayCardName(j.getString("payCardName"));
/* 173 */                     sc.setPayCardNo(j.getString("payCardNo"));
/*     */                     
/* 175 */                     SavedCard.this.SavedCardList.add(sc);
/*     */                     
/* 177 */                     SavedCard.this.cvv_list.add("");
/*     */                   } 
/*     */                 } 
/*     */                 
/* 181 */                 SavedCard.this.adapter.notifyDataSetChanged();
/* 182 */                 SavedCard.this.viewPager.setAdapter(SavedCard.this.adapter);
/* 183 */               } catch (JSONException e) {
/* 184 */                 e.printStackTrace();
/*     */               }
/*     */             
/* 187 */             } else if (position == 1) {
/* 188 */               SavedCard.this.SavedCardList.clear();
/* 189 */               SavedCard.this.cvv_list.clear();
/*     */               
/*     */               try {
/* 192 */                 for (int i = 0; i < SavedCard.this.vJsonArr.length(); i++) {
/*     */                   
/* 194 */                   JSONObject j = SavedCard.this.vJsonArr.getJSONObject(i);
/* 195 */                   if (j.getString("payCardType").equalsIgnoreCase("DBCRD")) {
/* 196 */                     SaveCard sc = new SaveCard();
/* 197 */                     sc.setPayOptId(j.getString("payOptId"));
/* 198 */                     sc.setPayOption(j.getString("payOption"));
/* 199 */                     sc.setPayCardType(j.getString("payCardType"));
/* 200 */                     sc.setPayCardName(j.getString("payCardName"));
/* 201 */                     sc.setPayCardNo(j.getString("payCardNo"));
/* 202 */                     SavedCard.this.cvv_list.add("");
/* 203 */                     SavedCard.this.SavedCardList.add(sc);
/*     */                   } 
/*     */                 } 
/*     */ 
/*     */ 
/*     */                 
/* 209 */                 SavedCard.this.adapter.notifyDataSetChanged();
/* 210 */                 SavedCard.this.viewPager.setAdapter(SavedCard.this.adapter);
/* 211 */               } catch (JSONException e) {
/* 212 */                 e.printStackTrace();
/*     */               } 
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   SaveCard sv;
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 226 */     int id = v.getId();
/*     */     
/* 228 */     if (id == R.id.expand) {
/* 229 */       if (this.expandableLayout0.isExpanded()) {
/* 230 */         this.expand.setVisibility(0);
/* 231 */         this.expandableLayout0.collapse();
/*     */       } else {
/* 233 */         this.expand.setVisibility(8);
/* 234 */         this.expandableLayout0.expand();
/*     */       }
/*     */     
/* 237 */     } else if (id == R.id.up) {
/* 238 */       if (this.expandableLayout0.isExpanded()) {
/*     */         
/* 240 */         this.expand.setVisibility(0);
/* 241 */         this.expandableLayout0.collapse();
/*     */       } else {
/* 243 */         this.expand.setVisibility(0);
/* 244 */         this.expandableLayout0.expand();
/*     */       }
/*     */     
/* 247 */     } else if (id == R.id.pay_card) {
/* 248 */       Toast.makeText(getApplicationContext(), this.cvv_list.get(this.viewPager.getCurrentItem()), 1).show();
/* 249 */       if (((String)this.cvv_list.get(this.viewPager.getCurrentItem())).length() == 3) {
/*     */ 
/*     */         
/* 252 */         String vOrderId = ServiceUtility.chkNull(this.merchant.getOrder_id()).toString().trim();
/* 253 */         String vRsaKeyUrl = ServiceUtility.chkNull(this.merchant.getRsa_url()).toString().trim();
/* 254 */         if (!vOrderId.equals("") && !vRsaKeyUrl.equals("")) {
/* 255 */           Intent intent = new Intent((Context)this, WebViewActivity.class);
/* 256 */           intent.putExtra("order_id", ServiceUtility.chkNull(this.merchant.getOrder_id()).toString().trim());
/* 257 */           intent.putExtra("access_code", ServiceUtility.chkNull(this.merchant.getAccess_code()).toString().trim());
/* 258 */           intent.putExtra("merchant_id", ServiceUtility.chkNull(this.merchant.getMerchant_id()).toString().trim());
/* 259 */           intent.putExtra("billing_name", ServiceUtility.chkNull(this.billing.getName()).toString().trim());
/* 260 */           intent.putExtra("billing_address", ServiceUtility.chkNull(this.billing.getAddress()).toString().trim());
/* 261 */           intent.putExtra("billing_country", ServiceUtility.chkNull(this.billing.getCountry()).toString().trim());
/* 262 */           intent.putExtra("billing_state", ServiceUtility.chkNull(this.billing.getState()).toString().trim());
/* 263 */           intent.putExtra("billing_city", ServiceUtility.chkNull(this.billing.getCity()).toString().trim());
/* 264 */           intent.putExtra("billing_zip", "413709");
/*     */           
/* 266 */           intent.putExtra("billing_tel", ServiceUtility.chkNull(this.billing.getTelephone()).toString().trim());
/* 267 */           intent.putExtra("billing_email", ServiceUtility.chkNull(this.billing.getEmail()).toString().trim());
/* 268 */           intent.putExtra("delivery_name", ServiceUtility.chkNull(this.shipping.getName()).toString().trim());
/* 269 */           intent.putExtra("delivery_address", ServiceUtility.chkNull(this.shipping.getAddress()).toString().trim());
/* 270 */           intent.putExtra("delivery_country", ServiceUtility.chkNull(this.shipping.getCountry()).toString().trim());
/* 271 */           intent.putExtra("delivery_state", ServiceUtility.chkNull(this.shipping.getState()).toString().trim());
/* 272 */           intent.putExtra("delivery_city", ServiceUtility.chkNull(this.shipping.getCity()).toString().trim());
/* 273 */           intent.putExtra("delivery_zip", "413709");
/* 274 */           intent.putExtra("delivery_tel", ServiceUtility.chkNull(this.shipping.getTelephone()).toString().trim());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 280 */           intent.putExtra("cvv_number", ServiceUtility.chkNull(this.cvv_list.get(this.viewPager.getCurrentItem())).toString().trim());
/* 281 */           intent.putExtra("redirect_url", ServiceUtility.chkNull(this.merchant.getRedirect_url()).toString().trim());
/* 282 */           intent.putExtra("cancel_url", ServiceUtility.chkNull(this.merchant.getCancel_url()).toString().trim());
/* 283 */           intent.putExtra("rsa_key_url", ServiceUtility.chkNull(this.merchant.getRsa_url()).toString().trim());
/* 284 */           intent.putExtra("payment_option", ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayOption());
/* 285 */           intent.putExtra("card_number", ServiceUtility.chkNull(((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardNo()).toString().trim());
/* 286 */           intent.putExtra("expiry_year", ServiceUtility.chkNull("").toString().trim());
/* 287 */           intent.putExtra("expiry_month", ServiceUtility.chkNull("").toString().trim());
/* 288 */           intent.putExtra("issuing_bank", ServiceUtility.chkNull("").toString().trim());
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
/* 321 */           intent.putExtra("card_type", ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardType());
/* 322 */           intent.putExtra("card_name", ((SaveCard)this.SavedCardList.get(this.viewPager.getCurrentItem())).getPayCardName());
/* 323 */           intent.putExtra("data_accept", "");
/* 324 */           intent.putExtra("customer_identifier", this.merchant.getCustomer_id());
/* 325 */           intent.putExtra("currency", ServiceUtility.chkNull(this.merchant.getCurrency()).toString().trim());
/* 326 */           intent.putExtra("amount", ServiceUtility.chkNull(this.merchant.getAmount()).toString().trim());
/*     */ 
/*     */ 
/*     */           
/* 330 */           startActivity(intent);
/*     */         }
/*     */       
/*     */       }
/*     */       else {
/*     */         
/* 336 */         Toast.makeText(getApplicationContext(), "Enter valid CVV", 1).show();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void updateTotalValue(String str) {
/* 346 */     this.cvv_list.set(this.viewPager.getCurrentItem(), str);
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/SavedCard.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */