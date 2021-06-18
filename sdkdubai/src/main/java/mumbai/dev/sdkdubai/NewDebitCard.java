/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.text.Editable;
/*     */ import android.text.TextUtils;
/*     */ import android.text.TextWatcher;
/*     */ import android.view.View;
/*     */ import android.widget.ArrayAdapter;
/*     */ import android.widget.Button;
/*     */ import android.widget.CheckBox;
/*     */ import android.widget.EditText;
/*     */ import android.widget.Spinner;
/*     */ import android.widget.SpinnerAdapter;
/*     */ import android.widget.TextView;
/*     */ import android.widget.Toast;
/*     */ import androidx.appcompat.app.AppCompatActivity;
/*     */ import androidx.viewpager.widget.PagerAdapter;
/*     */ import androidx.viewpager.widget.ViewPager;
/*     */ import java.util.ArrayList;
/*     */ import mumbai.dev.sdkdubai.dto.CardTypeDTO;
/*     */ import net.cachapa.expandablelayout.ExpandableLayout;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NewDebitCard
/*     */   extends AppCompatActivity
/*     */   implements View.OnClickListener
/*     */ {
/*     */   ViewPager viewPager;
/*     */   PagerAdapter adapter;
/*     */   private ExpandableLayout expandableLayout0;
/*     */   TextView expand;
/*     */   TextView up;
/*     */   Context mContext;
/*     */   JSONArray vJsonArr;
/*     */   JSONArray cardtypejson;
/*     */   Spinner dropdown;
/*  44 */   ArrayList<CardTypeDTO> card_list = new ArrayList<>();
/*  45 */   ArrayList<String> card_list_name = new ArrayList<>();
/*     */   
/*     */   ArrayAdapter<String> spinnerAdapter;
/*     */   Button pay_card;
/*     */   EditText ip_exp;
/*     */   EditText ip_cvv;
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/*  53 */     super.onCreate(savedInstanceState);
/*  54 */     setContentView(R.layout.activity_new_debit_card);
/*  55 */     this.dropdown = (Spinner)findViewById(R.id.spinner1);
/*  56 */     this.pay_card = (Button)findViewById(R.id.pay_card);
/*  57 */     this.ip_cvv = (EditText)findViewById(R.id.ip_cvv);
/*  58 */     this.ip_exp = (EditText)findViewById(R.id.ip_exp);
/*  59 */     this.ip_cardno = (EditText)findViewById(R.id.ip_cardno);
/*  60 */     this.fee_checkbox = (CheckBox)findViewById(R.id.checkBox1);
/*     */ 
/*     */     
/*  63 */     this.pay_card.setText("PAY " + PaymentOptions.merchant.currency + " " + PaymentOptions.merchant.amount);
/*  64 */     this.tv_inr = (TextView)findViewById(R.id.tv_inr);
/*  65 */     this.tv_orderid = (TextView)findViewById(R.id.tv_orderid);
/*  66 */     this.tv_inr.setText(PaymentOptions.merchant.currency + " " + PaymentOptions.merchant.amount);
/*  67 */     this.tv_orderid.setText("Order #: " + PaymentOptions.merchant.order_id);
/*  68 */     if (Constants.VAULT.equalsIgnoreCase("Y")) {
/*  69 */       this.fee_checkbox.setVisibility(0);
/*     */     } else {
/*  71 */       this.fee_checkbox.setVisibility(8);
/*     */     } 
/*  73 */     this.ip_exp.addTextChangedListener(new TextWatcher()
/*     */         {
/*     */           public void afterTextChanged(Editable editable)
/*     */           {
/*  77 */             if (editable.length() > 0 && editable.length() % 3 == 0) {
/*  78 */               char c = editable.charAt(editable.length() - 1);
/*  79 */               if ('/' == c) {
/*  80 */                 editable.delete(editable.length() - 1, editable.length());
/*     */               }
/*     */             } 
/*  83 */             if (editable.length() > 0 && editable.length() % 3 == 0) {
/*  84 */               char c = editable.charAt(editable.length() - 1);
/*  85 */               if (Character.isDigit(c) && (TextUtils.split(editable.toString(), String.valueOf("/"))).length <= 2) {
/*  86 */                 editable.insert(editable.length() - 1, String.valueOf("/"));
/*     */               }
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onTextChanged(CharSequence s, int start, int before, int count) {}
/*     */         });
/* 101 */     this.mContext = (Context)this;
/*     */     try {
/* 103 */       this.vJsonArr = new JSONArray(getIntent().getStringExtra("Payment_Type"));
/*     */ 
/*     */       
/* 106 */       for (int i = 0; i < this.vJsonArr.length(); i++) {
/* 107 */         JSONObject jSONObject = this.vJsonArr.getJSONObject(i);
/* 108 */         if (jSONObject.getString("payOpt").equalsIgnoreCase("OPTDBCRD"))
/*     */         {
/* 110 */           this.cardtypejson = new JSONArray(jSONObject.getString("cardsList"));
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 115 */       this.card_list.clear();
/* 116 */       this.card_list_name.clear();
/* 117 */       this.card_list_name.add("We Accept");
/* 118 */       for (int j = 0; j < this.cardtypejson.length(); j++) {
/* 119 */         JSONObject card = this.cardtypejson.getJSONObject(j);
/* 120 */         CardTypeDTO cardTypeDTO = new CardTypeDTO();
/* 121 */         cardTypeDTO.setCardName(card.getString("cardName"));
/* 122 */         cardTypeDTO.setCardType(card.getString("cardType"));
/* 123 */         cardTypeDTO.setPayOptType(card.getString("payOptType"));
/* 124 */         cardTypeDTO.setDataAcceptedAt(card.getString("dataAcceptedAt"));
/* 125 */         cardTypeDTO.setStatus(card.getString("status"));
/* 126 */         this.card_list.add(cardTypeDTO);
/* 127 */         this.card_list_name.add(card.getString("cardName"));
/*     */       } 
/* 129 */     } catch (JSONException e) {
/* 130 */       e.printStackTrace();
/*     */     } 
/* 132 */     this.expandableLayout0 = (ExpandableLayout)findViewById(R.id.expandable_layout_01);
/*     */     
/* 134 */     this.expand = (TextView)findViewById(R.id.expand);
/* 135 */     this.up = (TextView)findViewById(R.id.up);
/* 136 */     this.pay_card.setOnClickListener(this);
/* 137 */     this.spinnerAdapter = new ArrayAdapter((Context)this, 17367048, this.card_list_name);
/*     */     
/* 139 */     this.spinnerAdapter
/* 140 */       .setDropDownViewResource(17367049);
/* 141 */     this.dropdown.setAdapter((SpinnerAdapter)this.spinnerAdapter);
/* 142 */     this.expand.setOnClickListener(this);
/* 143 */     this.up.setOnClickListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   EditText ip_cardno;
/*     */ 
/*     */   
/*     */   CheckBox fee_checkbox;
/*     */ 
/*     */   
/*     */   TextView tv_inr;
/*     */ 
/*     */   
/*     */   TextView tv_orderid;
/*     */ 
/*     */   
/*     */   private static final String TAG = "NewDebitCard";
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 165 */     int id = v.getId();
/*     */     
/* 167 */     if (id == R.id.expand) {
/* 168 */       if (this.expandableLayout0.isExpanded()) {
/* 169 */         this.expand.setVisibility(0);
/* 170 */         this.expandableLayout0.collapse();
/*     */       } else {
/* 172 */         this.expand.setVisibility(8);
/* 173 */         this.expandableLayout0.expand();
/*     */       }
/*     */     
/* 176 */     } else if (id == R.id.up) {
/* 177 */       if (this.expandableLayout0.isExpanded()) {
/*     */         
/* 179 */         this.expand.setVisibility(0);
/* 180 */         this.expandableLayout0.collapse();
/*     */       } else {
/* 182 */         this.expand.setVisibility(0);
/* 183 */         this.expandableLayout0.expand();
/*     */       }
/*     */     
/* 186 */     } else if (id == R.id.pay_card) {
/* 187 */       if (this.dropdown.getSelectedItemPosition() < 1) {
/*     */         
/* 189 */         showToast("Please select Debit card type");
/*     */ 
/*     */       
/*     */       }
/* 193 */       else if (this.ip_cardno.getText().toString().length() != 16) {
/*     */         
/* 195 */         showToast("Please enter valid debit card number");
/*     */       }
/* 197 */       else if (this.ip_exp.getText().toString().length() != 5) {
/*     */         
/* 199 */         showToast("Please enter valid expiry date");
/*     */       }
/* 201 */       else if (this.ip_cvv.getText().toString().length() != 3) {
/*     */         
/* 203 */         showToast("Please enter valid CVV");
/*     */       }
/*     */       else {
/*     */         
/* 207 */         String vOrderId = ServiceUtility.chkNull(PaymentOptions.merchant.getOrder_id()).toString().trim();
/* 208 */         String vRsaKeyUrl = ServiceUtility.chkNull(PaymentOptions.merchant.getRsa_url()).toString().trim();
/* 209 */         if (!vOrderId.equals("") && !vRsaKeyUrl.equals("")) {
/* 210 */           Intent intent = new Intent((Context)this, WebViewActivity.class);
/* 211 */           intent.putExtra("order_id", ServiceUtility.chkNull(PaymentOptions.merchant.getOrder_id()).toString().trim());
/* 212 */           intent.putExtra("access_code", ServiceUtility.chkNull(PaymentOptions.merchant.getAccess_code()).toString().trim());
/* 213 */           intent.putExtra("merchant_id", ServiceUtility.chkNull(PaymentOptions.merchant.getMerchant_id()).toString().trim());
/* 214 */           intent.putExtra("billing_name", ServiceUtility.chkNull(PaymentOptions.billing.getName()).toString().trim());
/* 215 */           intent.putExtra("billing_address", ServiceUtility.chkNull(PaymentOptions.billing.getAddress()).toString().trim());
/* 216 */           intent.putExtra("billing_country", ServiceUtility.chkNull(PaymentOptions.billing.getCountry()).toString().trim());
/* 217 */           intent.putExtra("billing_state", ServiceUtility.chkNull(PaymentOptions.billing.getState()).toString().trim());
/* 218 */           intent.putExtra("billing_city", ServiceUtility.chkNull(PaymentOptions.billing.getCity()).toString().trim());
/*     */           
/* 220 */           intent.putExtra("billing_tel", ServiceUtility.chkNull(PaymentOptions.billing.getTelephone()).toString().trim());
/* 221 */           intent.putExtra("billing_email", ServiceUtility.chkNull(PaymentOptions.billing.getEmail()).toString().trim());
/* 222 */           intent.putExtra("delivery_name", ServiceUtility.chkNull(PaymentOptions.shipping.getName()).toString().trim());
/* 223 */           intent.putExtra("delivery_address", ServiceUtility.chkNull(PaymentOptions.shipping.getAddress()).toString().trim());
/* 224 */           intent.putExtra("delivery_country", ServiceUtility.chkNull(PaymentOptions.shipping.getCountry()).toString().trim());
/* 225 */           intent.putExtra("delivery_state", ServiceUtility.chkNull(PaymentOptions.shipping.getState()).toString().trim());
/* 226 */           intent.putExtra("delivery_city", ServiceUtility.chkNull(PaymentOptions.shipping.getCity()).toString().trim());
/*     */           
/* 228 */           intent.putExtra("delivery_tel", ServiceUtility.chkNull(PaymentOptions.shipping.getTelephone()).toString().trim());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 234 */           intent.putExtra("cvv_number", this.ip_cvv.getText().toString());
/* 235 */           intent.putExtra("redirect_url", ServiceUtility.chkNull(PaymentOptions.merchant.getRedirect_url()).toString().trim());
/* 236 */           intent.putExtra("cancel_url", ServiceUtility.chkNull(PaymentOptions.merchant.getCancel_url()).toString().trim());
/* 237 */           intent.putExtra("rsa_key_url", ServiceUtility.chkNull(PaymentOptions.merchant.getRsa_url()).toString().trim());
/* 238 */           intent.putExtra("payment_option", ((CardTypeDTO)this.card_list.get(this.dropdown.getSelectedItemPosition() - 1)).getPayOptType());
/* 239 */           intent.putExtra("card_number", ServiceUtility.chkNull(this.ip_cardno.getText().toString()).toString().trim());
/* 240 */           intent.putExtra("expiry_month", ServiceUtility.chkNull(this.ip_exp.getText().toString().substring(0, 2)).toString().trim());
/* 241 */           intent.putExtra("expiry_year", ServiceUtility.chkNull("20" + this.ip_exp.getText().toString().substring(3, 5)).toString().trim());
/* 242 */           intent.putExtra("issuing_bank", "");
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
/* 275 */           intent.putExtra("card_type", ((CardTypeDTO)this.card_list.get(this.dropdown.getSelectedItemPosition() - 1)).getCardType());
/* 276 */           intent.putExtra("card_name", ((CardTypeDTO)this.card_list.get(this.dropdown.getSelectedItemPosition() - 1)).getCardName());
/* 277 */           if (((CardTypeDTO)this.card_list.get(this.dropdown.getSelectedItemPosition() - 1)).getDataAcceptedAt().equalsIgnoreCase("CCAvenue")) {
/* 278 */             intent.putExtra("data_accept", "Y");
/*     */           } else {
/* 280 */             intent.putExtra("data_accept", "N");
/* 281 */           }  intent.putExtra("customer_identifier", PaymentOptions.merchant.getCustomer_id());
/* 282 */           intent.putExtra("currency", ServiceUtility.chkNull(PaymentOptions.merchant.getCurrency()).toString().trim());
/* 283 */           intent.putExtra("amount", ServiceUtility.chkNull(PaymentOptions.merchant.getAmount()).toString().trim());
/*     */           
/* 285 */           if (this.fee_checkbox.isChecked())
/* 286 */             intent.putExtra("saveCard", "Y"); 
/* 287 */           startActivity(intent);
/*     */         } else {
/* 289 */           showToast("Amount/Currency/Access code/Merchant Id & RSA key Url are mandatory.");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void showToast(String msg) {
/* 297 */     Toast.makeText((Context)this, msg, 1).show();
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/NewDebitCard.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */