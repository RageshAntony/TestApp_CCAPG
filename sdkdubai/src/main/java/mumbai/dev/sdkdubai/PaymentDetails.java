/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.content.Context;
/*     */ import android.content.Intent;
/*     */ import android.os.Bundle;
/*     */ import android.util.Log;
/*     */ import android.view.View;
/*     */ import android.widget.AdapterView;
/*     */ import android.widget.ArrayAdapter;
/*     */ import android.widget.Button;
/*     */ import android.widget.CheckBox;
/*     */ import android.widget.EditText;
/*     */ import android.widget.LinearLayout;
/*     */ import android.widget.RadioButton;
/*     */ import android.widget.RadioGroup;
/*     */ import android.widget.RelativeLayout;
/*     */ import android.widget.Spinner;
/*     */ import android.widget.SpinnerAdapter;
/*     */ import android.widget.TextView;
/*     */ import android.widget.Toast;
/*     */ import androidx.appcompat.app.AppCompatActivity;
/*     */ import com.android.volley.Request;
/*     */ import com.android.volley.RequestQueue;
/*     */ import com.android.volley.Response;
/*     */ import com.android.volley.VolleyError;
/*     */ import com.android.volley.toolbox.StringRequest;
/*     */ import com.android.volley.toolbox.Volley;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Map;
/*     */ import mumbai.dev.sdkdubai.adapter.CardAdapter;
/*     */ import mumbai.dev.sdkdubai.adapter.PayOptAdapter;
/*     */ import mumbai.dev.sdkdubai.dto.CardTypeDTO;
/*     */ import mumbai.dev.sdkdubai.dto.EMIOptionDTO;
/*     */ import mumbai.dev.sdkdubai.dto.PaymentOptionDTO;
/*     */ import net.cachapa.expandablelayout.ExpandableLayout;
/*     */ import org.json.JSONArray;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import utility.ServiceUtility;
/*     */ 
/*     */ 
/*     */ public class PaymentDetails
/*     */   extends AppCompatActivity
/*     */   implements View.OnClickListener
/*     */ {
/*     */   ExpandableLayout exp_billing_address;
/*     */   ExpandableLayout exp_shipping_address;
/*     */   Button btn_billing_address;
/*     */   Button btn_shipping_address;
/*     */   Button sdk;
/*     */   EditText ed_address_b;
/*     */   EditText ed_name_b;
/*     */   EditText ed_country_b;
/*     */   EditText ed_state_b;
/*     */   EditText ed_city_b;
/*     */   EditText ed_zip_b;
/*     */   EditText ed_telephone_b;
/*     */   EditText ed_email_b;
/*     */   EditText ed_address_s;
/*  63 */   Map<String, ArrayList<CardTypeDTO>> cardsList = new LinkedHashMap<>(); EditText ed_name_s; EditText ed_country_s; EditText ed_state_s; EditText ed_city_s; EditText ed_zip_s; EditText ed_telephone_s; EditText ed_email_s; RelativeLayout address; MerchantDetails merchant; BillingAddress billing; ShippingAddress shipping; Spinner payment_type; Button pay; ArrayAdapter<String> spinnerAdapter;
/*  64 */   ArrayList<PaymentOptionDTO> payOptionList = new ArrayList<>();
/*  65 */   ArrayList<EMIOptionDTO> emiOptionList = new ArrayList<>();
/*     */   private EditText cardNumber;
/*     */   private EditText cardCvv;
/*     */   private EditText expiryMonth;
/*     */   private EditText expiryYear;
/*     */   private EditText issuingBank;
/*     */   String selectedPaymentOption;
/*     */   CardTypeDTO selectedCardType;
/*  73 */   private Map<String, String> paymentOptions = new LinkedHashMap<>(); JSONObject jsonRespObj;
/*     */   
/*     */   protected void onCreate(Bundle savedInstanceState) {
/*  76 */     super.onCreate(savedInstanceState);
/*  77 */     setContentView(R.layout.activity_payment_details);
/*  78 */     this.payment_type = (Spinner)findViewById(R.id.payment_type);
/*  79 */     Intent intent = getIntent();
/*  80 */     this.merchant = (MerchantDetails)intent.getParcelableExtra("merchant");
/*  81 */     this.billing = (BillingAddress)intent.getParcelableExtra("billing");
/*  82 */     this.shipping = (ShippingAddress)intent.getParcelableExtra("shipping");
/*  83 */     this.pay = (Button)findViewById(R.id.pay);
/*     */     
/*  85 */     this.cardNumber = (EditText)findViewById(R.id.cardNumber);
/*  86 */     this.cardCvv = (EditText)findViewById(R.id.cardCVV);
/*  87 */     this.expiryYear = (EditText)findViewById(R.id.expiryYear);
/*  88 */     this.expiryMonth = (EditText)findViewById(R.id.expiryMonth);
/*  89 */     this.issuingBank = (EditText)findViewById(R.id.issuingBank);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  96 */     this.address = (RelativeLayout)findViewById(R.id.address);
/*     */     
/*  98 */     this.ed_address_s = (EditText)findViewById(R.id.ed_address_s);
/*  99 */     this.ed_name_s = (EditText)findViewById(R.id.ed_name_s);
/* 100 */     this.ed_country_s = (EditText)findViewById(R.id.ed_country_s);
/* 101 */     this.ed_state_s = (EditText)findViewById(R.id.ed_state_s);
/* 102 */     this.ed_city_s = (EditText)findViewById(R.id.ed_city_s);
/* 103 */     this.ed_zip_s = (EditText)findViewById(R.id.ed_zip_s);
/* 104 */     this.ed_telephone_s = (EditText)findViewById(R.id.ed_telephone_s);
/* 105 */     this.ed_email_s = (EditText)findViewById(R.id.ed_email_s);
/*     */     
/* 107 */     this.ed_address_s.setText(this.shipping.getAddress());
/* 108 */     this.ed_name_s.setText(this.shipping.getName());
/* 109 */     this.ed_country_s.setText(this.shipping.getCountry());
/* 110 */     this.ed_state_s.setText(this.shipping.getState());
/* 111 */     this.ed_city_s.setText(this.shipping.getCity());
/*     */     
/* 113 */     this.ed_telephone_s.setText(this.shipping.getTelephone());
/*     */ 
/*     */ 
/*     */     
/* 117 */     this.ed_address_b = (EditText)findViewById(R.id.ed_address_b);
/* 118 */     this.ed_name_b = (EditText)findViewById(R.id.ed_name_b);
/* 119 */     this.ed_country_b = (EditText)findViewById(R.id.ed_country_b);
/* 120 */     this.ed_state_b = (EditText)findViewById(R.id.ed_state_b);
/* 121 */     this.ed_city_b = (EditText)findViewById(R.id.ed_city_b);
/* 122 */     this.ed_zip_b = (EditText)findViewById(R.id.ed_zip_b);
/* 123 */     this.ed_telephone_b = (EditText)findViewById(R.id.ed_telephone_b);
/* 124 */     this.ed_email_b = (EditText)findViewById(R.id.ed_email_b);
/*     */ 
/*     */     
/* 127 */     this.ed_address_b.setText(this.billing.getAddress());
/* 128 */     this.ed_name_b.setText(this.billing.getName());
/* 129 */     this.ed_country_b.setText(this.billing.getCountry());
/* 130 */     this.ed_state_b.setText(this.billing.getState());
/* 131 */     this.ed_city_b.setText(this.billing.getCity());
/*     */     
/* 133 */     this.ed_telephone_b.setText(this.billing.getTelephone());
/* 134 */     this.ed_email_b.setText(this.billing.getEmail());
/*     */     
/* 136 */     this.btn_billing_address = (Button)findViewById(R.id.btn_billing_address);
/* 137 */     this.btn_shipping_address = (Button)findViewById(R.id.btn_shipping_address);
/*     */ 
/*     */     
/* 140 */     this.exp_billing_address = (ExpandableLayout)findViewById(R.id.exp_billing_address);
/* 141 */     this.exp_shipping_address = (ExpandableLayout)findViewById(R.id.exp_shipping_address);
/*     */ 
/*     */ 
/*     */     
/* 145 */     this.btn_billing_address.setOnClickListener(this);
/* 146 */     this.btn_shipping_address.setOnClickListener(this);
/*     */ 
/*     */     
/* 149 */     if (this.merchant.isShow_addr()) {
/* 150 */       this.address.setVisibility(0);
/*     */     } else {
/*     */       
/* 153 */       this.address.setVisibility(8);
/*     */     } 
/* 155 */     boolean chk = validate();
/* 156 */     if (chk) {
/* 157 */       fetch_payment_details();
/*     */     } else {
/* 159 */       finish();
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 164 */     this.pay.setOnClickListener(new View.OnClickListener()
/*     */         {
/*     */           public void onClick(View v) {
/* 167 */             String vOrderId = PaymentDetails.this.merchant.getOrder_id();
/* 168 */             String vRsaKeyUrl = PaymentDetails.this.merchant.getRsa_url();
/* 169 */             if (PaymentDetails.this.selectedCardType != null && PaymentDetails.this.selectedPaymentOption != null && !vOrderId.equals("") && !vRsaKeyUrl.equals("")) {
/* 170 */               Intent intent = new Intent((Context)PaymentDetails.this, WebViewActivity.class);
/* 171 */               intent.putExtra("order_id", PaymentDetails.this.merchant.getOrder_id().toString());
/* 172 */               intent.putExtra("access_code", PaymentDetails.this.merchant.getAccess_code());
/* 173 */               intent.putExtra("merchant_id", PaymentDetails.this.merchant.getMerchant_id());
/* 174 */               intent.putExtra("billing_name", PaymentDetails.this.billing.getName());
/* 175 */               intent.putExtra("billing_address", PaymentDetails.this.billing.getAddress());
/* 176 */               intent.putExtra("billing_country", PaymentDetails.this.billing.getCountry());
/* 177 */               intent.putExtra("billing_state", PaymentDetails.this.billing.getState());
/* 178 */               intent.putExtra("billing_city", PaymentDetails.this.billing.getCity());
/*     */               
/* 180 */               intent.putExtra("billing_tel", PaymentDetails.this.billing.getTelephone());
/* 181 */               intent.putExtra("billing_email", PaymentDetails.this.billing.getEmail());
/* 182 */               intent.putExtra("delivery_name", PaymentDetails.this.shipping.getName());
/* 183 */               intent.putExtra("delivery_address", PaymentDetails.this.shipping.getAddress());
/* 184 */               intent.putExtra("delivery_country", PaymentDetails.this.shipping.getCountry());
/* 185 */               intent.putExtra("delivery_state", PaymentDetails.this.shipping.getState());
/* 186 */               intent.putExtra("delivery_city", PaymentDetails.this.shipping.getCity());
/*     */               
/* 188 */               intent.putExtra("delivery_tel", PaymentDetails.this.shipping.getTelephone());
/*     */               
/* 190 */               String cardCVV = ServiceUtility.chkNull(PaymentDetails.this.cardCvv.getText()).toString().trim();
/*     */ 
/*     */ 
/*     */               
/* 194 */               intent.putExtra("cvv_number", cardCVV);
/* 195 */               intent.putExtra("redirect_url", PaymentDetails.this.merchant.getRedirect_url());
/* 196 */               intent.putExtra("cancel_url", PaymentDetails.this.merchant.getCancel_url());
/* 197 */               intent.putExtra("rsa_key_url", PaymentDetails.this.merchant.getRsa_url());
/* 198 */               intent.putExtra("payment_option", PaymentDetails.this.selectedPaymentOption);
/* 199 */               intent.putExtra("card_number", ServiceUtility.chkNull(PaymentDetails.this.cardNumber.getText()).toString().trim());
/* 200 */               intent.putExtra("expiry_year", ServiceUtility.chkNull(PaymentDetails.this.expiryYear.getText()).toString().trim());
/* 201 */               intent.putExtra("expiry_month", ServiceUtility.chkNull(PaymentDetails.this.expiryMonth.getText()).toString().trim());
/* 202 */               intent.putExtra("issuing_bank", ServiceUtility.chkNull(PaymentDetails.this.issuingBank.getText()).toString().trim());
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 240 */               intent.putExtra("card_type", PaymentDetails.this.selectedCardType.getCardType());
/* 241 */               intent.putExtra("card_name", PaymentDetails.this.selectedCardType.getCardName());
/* 242 */               intent.putExtra("data_accept", (PaymentDetails.this.selectedCardType.getDataAcceptedAt() != null) ? (PaymentDetails.this.selectedCardType.getDataAcceptedAt().equals("CCAvenue") ? "Y" : "N") : null);
/* 243 */               intent.putExtra("customer_identifier", PaymentDetails.this.merchant.getCustomer_id());
/* 244 */               intent.putExtra("currency", PaymentDetails.this.merchant.getCurrency());
/* 245 */               intent.putExtra("amount", PaymentDetails.this.merchant.getAmount());
/*     */ 
/*     */ 
/*     */               
/* 249 */               PaymentDetails.this.startActivity(intent);
/*     */             } else {
/* 251 */               PaymentDetails.this.showToast("Amount/Currency/Access code/Merchant Id & RSA key Url are mandatory.");
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void fetch_payment_details() {
/* 261 */     LoadingDialog.showLoadingDialog((Context)this, "Loading...");
/*     */     
/* 263 */     StringRequest stringRequest = new StringRequest(1, "https://secure.ccavenue.ae/transaction/transaction.do", new Response.Listener<String>()
/*     */         {
/*     */           
/*     */           public void onResponse(String response)
/*     */           {
/* 268 */             LoadingDialog.cancelLoading();
/*     */ 
/*     */ 
/*     */             
/* 272 */             if (response != null && !response.equals("")) {
/* 273 */               PaymentDetails.this.payment_list(response);
/*     */             } else {
/*     */               
/* 276 */               Log.e("ServiceHandler", "Couldn't get any data from the url");
/*     */             } 
/*     */           }
/*     */         },new Response.ErrorListener()
/*     */         {
/*     */           public void onErrorResponse(VolleyError error)
/*     */           {
/* 283 */             LoadingDialog.cancelLoading();
/*     */           }
/*     */         })
/*     */       {
/*     */         protected Map<String, String> getParams()
/*     */         {
/* 289 */           Map<String, String> params = new HashMap<>();
/* 290 */           params.put("command", "getJsonDataVault");
/* 291 */           params.put("access_code", PaymentDetails.this.merchant.getAccess_code().trim());
/* 292 */           params.put("currency", PaymentDetails.this.merchant.getCurrency().trim());
/* 293 */           params.put("amount", PaymentDetails.this.merchant.getAmount().trim());
/* 294 */           params.put("customer_identifier", PaymentDetails.this.merchant.getCustomer_id().trim());
/* 295 */           return params;
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 300 */     RequestQueue requestQueue = Volley.newRequestQueue((Context)this);
/* 301 */     requestQueue.add((Request)stringRequest);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void payment_list(String response) {
/*     */     try {
/* 308 */       this.jsonRespObj = new JSONObject(response);
/* 309 */       if (this.jsonRespObj != null) {
/*     */         
/* 311 */         if (this.jsonRespObj.getString("payOptions") != null) {
/* 312 */           JSONArray vPayOptsArr = new JSONArray(this.jsonRespObj.getString("payOptions"));
/* 313 */           for (int i = 0; i < vPayOptsArr.length(); i++) {
/* 314 */             JSONObject vPaymentOption = vPayOptsArr.getJSONObject(i);
/* 315 */             if (!vPaymentOption.getString("payOpt").equals("OPTIVRS")) {
/* 316 */               this.payOptionList.add(new PaymentOptionDTO(vPaymentOption.getString("payOpt"), vPaymentOption.getString("payOptDesc").toString()));
/* 317 */               this.paymentOptions.put(vPaymentOption.getString("payOpt"), vPaymentOption.getString("payOptDesc"));
/*     */               try {
/* 319 */                 JSONArray vCardArr = new JSONArray(vPaymentOption.getString("cardsList"));
/* 320 */                 if (vCardArr.length() > 0) {
/* 321 */                   this.cardsList.put(vPaymentOption.getString("payOpt"), new ArrayList<>());
/* 322 */                   for (int j = 0; j < vCardArr.length(); j++) {
/* 323 */                     JSONObject card = vCardArr.getJSONObject(j);
/*     */                     try {
/* 325 */                       CardTypeDTO cardTypeDTO = new CardTypeDTO();
/* 326 */                       cardTypeDTO.setCardName(card.getString("cardName"));
/* 327 */                       cardTypeDTO.setCardType(card.getString("cardType"));
/* 328 */                       cardTypeDTO.setPayOptType(card.getString("payOptType"));
/* 329 */                       cardTypeDTO.setDataAcceptedAt(card.getString("dataAcceptedAt"));
/* 330 */                       cardTypeDTO.setStatus(card.getString("status"));
/*     */                       
/* 332 */                       ((ArrayList<CardTypeDTO>)this.cardsList.get(vPaymentOption.getString("payOpt"))).add(cardTypeDTO);
/* 333 */                     } catch (Exception e) {
/* 334 */                       Log.e("ServiceHandler", "Error parsing cardType", e);
/*     */                     } 
/*     */                   } 
/*     */                 } 
/* 338 */               } catch (Exception e) {
/* 339 */                 Log.e("ServiceHandler", "Error parsing payment option", e);
/*     */               } 
/*     */             } 
/*     */           } 
/* 343 */         }  if (this.jsonRespObj.getString("EmiBanks") != null && this.jsonRespObj.getString("EmiBanks").length() > 0 && this.jsonRespObj
/* 344 */           .getString("EmiPlans") != null && this.jsonRespObj.getString("EmiPlans").length() > 0) {
/* 345 */           this.paymentOptions.put("OPTEMI", "Credit Card EMI");
/* 346 */           this.payOptionList.add(new PaymentOptionDTO("OPTEMI", "Credit Card EMI"));
/*     */         } 
/*     */       } 
/* 349 */     } catch (JSONException e) {
/* 350 */       Log.e("ServiceHandler", "Error fetching data from server", (Throwable)e);
/*     */     } 
/*     */ 
/*     */     
/* 354 */     PayOptAdapter payOptAdapter = new PayOptAdapter((Activity)this, 17367048, this.payOptionList);
/* 355 */     this.payment_type.setAdapter((SpinnerAdapter)payOptAdapter);
/*     */     
/* 357 */     this.payment_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
/*     */         {
/*     */           public void onNothingSelected(AdapterView<?> parent) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onItemSelected(AdapterView parent, View view, int position, long id) {
/* 366 */             PaymentDetails.this.selectedPaymentOption = ((PaymentOptionDTO)PaymentDetails.this.payOptionList.get(position)).getPayOptId();
/* 367 */             String vCustPayments = null;
/*     */             try {
/* 369 */               vCustPayments = PaymentDetails.this.jsonRespObj.getString("CustPayments");
/* 370 */             } catch (Exception exception) {}
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
/* 383 */             LinearLayout ll = (LinearLayout)PaymentDetails.this.findViewById(R.id.cardDetails);
/* 384 */             if (PaymentDetails.this.selectedPaymentOption.equals("OPTDBCRD") || PaymentDetails.this.selectedPaymentOption
/* 385 */               .equals("OPTCRDC")) {
/* 386 */               ll.setVisibility(0);
/*     */             } else {
/* 388 */               ll.setVisibility(8);
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 395 */             if (!PaymentDetails.this.selectedPaymentOption.equals("OPTEMI")) {
/*     */ 
/*     */               
/* 398 */               ((Spinner)PaymentDetails.this.findViewById(R.id.cardtype)).setVisibility(0);
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 403 */               Spinner cardType = (Spinner)PaymentDetails.this.findViewById(R.id.cardtype);
/* 404 */               CardAdapter cardTypeAdapter = new CardAdapter((Activity)PaymentDetails.this, 17367048, PaymentDetails.this.cardsList.get(PaymentDetails.this.selectedPaymentOption));
/* 405 */               cardType.setAdapter((SpinnerAdapter)cardTypeAdapter);
/*     */               
/* 407 */               cardType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
/*     */                   {
/*     */                     public void onItemSelected(AdapterView parent, View view, int position, long id)
/*     */                     {
/* 411 */                       PaymentDetails.this.selectedCardType = ((ArrayList<CardTypeDTO>)PaymentDetails.this.cardsList.get(PaymentDetails.this.selectedPaymentOption)).get(position);
/* 412 */                       if (ServiceUtility.chkNull(PaymentDetails.this.selectedPaymentOption).equals("OPTCRDC") || 
/* 413 */                         ServiceUtility.chkNull(PaymentDetails.this.selectedPaymentOption).equals("OPTDBCRD")) {
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
/* 424 */                         PaymentDetails.this.cardNumber.setText("4639170030912721");
/* 425 */                         PaymentDetails.this.expiryMonth.setText("05");
/* 426 */                         PaymentDetails.this.expiryYear.setText("2020");
/* 427 */                         PaymentDetails.this.cardCvv.setText("328");
/* 428 */                         PaymentDetails.this.issuingBank.setText("HDFC Bank");
/* 429 */                         ((LinearLayout)PaymentDetails.this.findViewById(R.id.cardDetails)).setVisibility(0);
/*     */                       } 
/*     */                     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                     
/*     */                     public void onNothingSelected(AdapterView<?> parent) {}
/*     */                   });
/*     */             } 
/*     */           }
/*     */         });
/*     */     try {
/* 447 */       if (this.jsonRespObj != null) {
/* 448 */         final LinearLayout vVaultOptionsCont = new LinearLayout((Context)this);
/* 449 */         if (this.jsonRespObj.has("CustPayments")) {
/* 450 */           final JSONArray vJsonArr = new JSONArray(this.jsonRespObj.getString("CustPayments"));
/* 451 */           if (vJsonArr.length() > 0) {
/* 452 */             ((LinearLayout)findViewById(R.id.ll1)).setVisibility(8);
/* 453 */             ((LinearLayout)findViewById(R.id.cardDetails)).setVisibility(8);
/*     */             
/* 455 */             LinearLayout vDataContainer = (LinearLayout)findViewById(R.id.linDataCont);
/* 456 */             vDataContainer.setVisibility(0);
/*     */             
/* 458 */             vVaultOptionsCont.setId(R.id.vaultCont);
/* 459 */             vVaultOptionsCont.setOrientation(1);
/* 460 */             TextView tv = new TextView((Context)this);
/* 461 */             tv.setText("Vault Options");
/* 462 */             vVaultOptionsCont.addView((View)tv);
/*     */             
/* 464 */             RadioGroup rg = new RadioGroup((Context)this);
/* 465 */             rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
/*     */                   public void onCheckedChanged(RadioGroup group, int checkedId) {
/*     */                     try {
/* 468 */                       for (int i = 0; i < vJsonArr.length(); i++) {
/* 469 */                         JSONObject vVaultOpt = vJsonArr.getJSONObject(i);
/*     */                         
/* 471 */                         if (checkedId == Integer.parseInt(vVaultOpt.getString("payOptId"))) {
/* 472 */                           PaymentDetails.this.selectedCardType = new CardTypeDTO();
/* 473 */                           PaymentDetails.this.selectedCardType.setCardName(vVaultOpt.getString("payCardName"));
/* 474 */                           PaymentDetails.this.selectedCardType.setCardType(vVaultOpt.getString("payCardType"));
/* 475 */                           PaymentDetails.this.selectedCardType.setPayOptType(vVaultOpt.getString("payOption"));
/*     */                           
/* 477 */                           PaymentDetails.this.selectedPaymentOption = vVaultOpt.getString("payOption");
/*     */                           
/* 479 */                           if (PaymentDetails.this.selectedPaymentOption.equals("OPTCRDC") || PaymentDetails.this.selectedPaymentOption.equals("OPTDBCRD")) {
/* 480 */                             ((LinearLayout)PaymentDetails.this.findViewById(R.id.vCardCVVCont)).setVisibility(0);
/*     */                           } else {
/* 482 */                             ((LinearLayout)PaymentDetails.this.findViewById(R.id.vCardCVVCont)).setVisibility(8);
/*     */                           } 
/* 484 */                           String vCardStr = "";
/*     */                           try {
/* 486 */                             vCardStr = (vVaultOpt.getString("payCardNo") != null) ? vVaultOpt.getString("payCardNo") : PaymentDetails.this.cardNumber.getText().toString();
/* 487 */                           } catch (Exception exception) {}
/*     */ 
/*     */                           
/* 490 */                           PaymentDetails.this.cardNumber.setText(vCardStr);
/*     */                         } 
/*     */                       } 
/* 493 */                     } catch (Exception exception) {}
/*     */                   }
/*     */                 });
/*     */             
/* 497 */             for (int i = 0; i < vJsonArr.length(); i++) {
/* 498 */               JSONObject vVaultOpt = vJsonArr.getJSONObject(i);
/*     */               
/* 500 */               String vCardStr = "";
/*     */               try {
/* 502 */                 vCardStr = (vVaultOpt.getString("payCardNo") != null) ? (" - XXXX XXXX XXXX " + vVaultOpt.getString("payCardNo")) : "";
/* 503 */               } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 508 */               String vLblText = (String)this.paymentOptions.get(vVaultOpt.getString("payOption")) + " - " + vVaultOpt.getString("payCardName") + vCardStr;
/* 509 */               RadioButton rb = new RadioButton((Context)this);
/* 510 */               rb.setId(Integer.parseInt(vVaultOpt.getString("payOptId")));
/* 511 */               rb.setText(vLblText);
/* 512 */               rb.setTextSize(11.0F);
/*     */               
/* 514 */               rg.addView((View)rb);
/*     */             } 
/* 516 */             vVaultOptionsCont.addView((View)rg);
/*     */             
/* 518 */             vDataContainer.addView((View)vVaultOptionsCont);
/*     */             
/* 520 */             final CheckBox vChb = new CheckBox((Context)this);
/* 521 */             vChb.setText("Pay using other payment option");
/* 522 */             vChb.setOnClickListener(new View.OnClickListener()
/*     */                 {
/*     */                   public void onClick(View v) {
/* 525 */                     if (vChb.isChecked()) {
/* 526 */                       ((LinearLayout)PaymentDetails.this.findViewById(R.id.vCardCVVCont)).setVisibility(8);
/* 527 */                       PaymentDetails.this.selectedPaymentOption = ((PaymentOptionDTO)PaymentDetails.this.payment_type.getItemAtPosition(PaymentDetails.this.payment_type.getSelectedItemPosition())).getPayOptId();
/* 528 */                       ((LinearLayout)PaymentDetails.this.findViewById(R.id.ll1)).setVisibility(0);
/* 529 */                       if (PaymentDetails.this.selectedPaymentOption.equals("OPTDBCRD") || PaymentDetails.this.selectedPaymentOption
/* 530 */                         .equals("OPTCRDC")) {
/* 531 */                         ((LinearLayout)PaymentDetails.this.findViewById(R.id.cardDetails)).setVisibility(0);
/* 532 */                       } else if (PaymentDetails.this.selectedPaymentOption.equals("OPTEMI")) {
/*     */ 
/*     */                         
/* 535 */                         ((LinearLayout)PaymentDetails.this.findViewById(R.id.cardDetails)).setVisibility(0);
/*     */                       } else {
/* 537 */                         ((LinearLayout)PaymentDetails.this.findViewById(R.id.cardDetails)).setVisibility(8);
/* 538 */                       }  ((CheckBox)PaymentDetails.this.findViewById(R.id.saveCard)).setVisibility(0);
/* 539 */                       vVaultOptionsCont.setVisibility(8);
/*     */                     } else {
/* 541 */                       ((LinearLayout)PaymentDetails.this.findViewById(R.id.ll1)).setVisibility(8);
/* 542 */                       ((LinearLayout)PaymentDetails.this.findViewById(R.id.cardDetails)).setVisibility(8);
/*     */                       
/* 544 */                       ((CheckBox)PaymentDetails.this.findViewById(R.id.saveCard)).setVisibility(8);
/* 545 */                       vVaultOptionsCont.setVisibility(0);
/*     */                     } 
/*     */                   }
/*     */                 });
/* 549 */             vDataContainer.addView((View)vChb);
/*     */           } else {
/* 551 */             ((LinearLayout)findViewById(R.id.ll1)).setVisibility(0);
/*     */           } 
/*     */         } else {
/* 554 */           ((LinearLayout)findViewById(R.id.vCardCVVCont)).setVisibility(8);
/* 555 */           this.selectedPaymentOption = ((PaymentOptionDTO)this.payment_type.getItemAtPosition(this.payment_type.getSelectedItemPosition())).getPayOptId();
/* 556 */           ((LinearLayout)findViewById(R.id.ll1)).setVisibility(0);
/* 557 */           if (this.selectedPaymentOption.equals("OPTDBCRD") || this.selectedPaymentOption
/* 558 */             .equals("OPTCRDC")) {
/* 559 */             ((LinearLayout)findViewById(R.id.cardDetails)).setVisibility(0);
/* 560 */           } else if (this.selectedPaymentOption.equals("OPTEMI")) {
/*     */ 
/*     */             
/* 563 */             ((LinearLayout)findViewById(R.id.cardDetails)).setVisibility(0);
/*     */           } else {
/* 565 */             ((LinearLayout)findViewById(R.id.cardDetails)).setVisibility(8);
/* 566 */           }  ((CheckBox)findViewById(R.id.saveCard)).setVisibility(0);
/* 567 */           vVaultOptionsCont.setVisibility(8);
/*     */         }
/*     */       
/*     */       } 
/* 571 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void showToast(String msg) {
/* 577 */     Toast.makeText((Context)this, msg, 1).show();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean validate() {
/* 582 */     if (this.merchant.getAccess_code() == null || this.merchant.getAccess_code().length() < 1) {
/* 583 */       showToast("Invalid Access Code!!!");
/* 584 */       return false;
/*     */     } 
/*     */     
/* 587 */     if (this.merchant.getOrder_id() == null || this.merchant.getOrder_id().length() < 1) {
/* 588 */       showToast("Invalid Order id!!!");
/* 589 */       return false;
/*     */     } 
/*     */     
/* 592 */     if (this.merchant.getCurrency() == null || this.merchant.getCurrency().length() < 1) {
/* 593 */       showToast("Invalid Currency!!!");
/* 594 */       return false;
/*     */     } 
/*     */     
/* 597 */     if (this.merchant.getAmount() == null || this.merchant.getAmount().length() < 1) {
/* 598 */       showToast("Invalid Amount!!!");
/* 599 */       return false;
/*     */     } 
/*     */     
/* 602 */     if (this.merchant.getMerchant_id() == null || this.merchant.getMerchant_id().length() < 1) {
/* 603 */       showToast("Invalid Merchant id!!!");
/* 604 */       return false;
/*     */     } 
/*     */     
/* 607 */     if (this.merchant.getRedirect_url() == null || this.merchant.getRedirect_url().length() < 1) {
/* 608 */       showToast("ouch .. Invalid redirect url!!!");
/* 609 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 613 */     if (this.merchant.getCancel_url() == null || this.merchant.getCancel_url().length() < 1) {
/* 614 */       showToast("Invalid cancel url!!!");
/* 615 */       return false;
/*     */     } 
/*     */     
/* 618 */     if (this.merchant.getRsa_url() == null || this.merchant.getRsa_url().length() < 1) {
/* 619 */       showToast("Invalid rsa url!!!");
/* 620 */       return false;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */     
/* 689 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void onClick(View v) {
/* 696 */     int id = v.getId();
/* 697 */     if (id == R.id.btn_billing_address) {
/*     */       
/* 699 */       this.exp_billing_address.toggle();
/*     */       
/* 701 */       if (this.exp_billing_address.isExpanded()) {
/* 702 */         this.btn_billing_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_more, 0);
/*     */       } else {
/* 704 */         this.btn_billing_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_less, 0);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 709 */       if (this.exp_shipping_address.isExpanded()) {
/* 710 */         this.exp_shipping_address.toggle();
/* 711 */         this.btn_shipping_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_less, 0);
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/* 718 */     else if (id == R.id.btn_shipping_address) {
/*     */       
/* 720 */       this.exp_shipping_address.toggle();
/*     */       
/* 722 */       if (this.exp_shipping_address.isExpanded()) {
/* 723 */         this.btn_shipping_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_more, 0);
/*     */       } else {
/* 725 */         this.btn_shipping_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_less, 0);
/*     */       } 
/*     */       
/* 728 */       if (this.exp_billing_address.isExpanded()) {
/* 729 */         this.exp_billing_address.toggle();
/* 730 */         this.btn_billing_address.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.expand_less, 0);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/PaymentDetails.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */