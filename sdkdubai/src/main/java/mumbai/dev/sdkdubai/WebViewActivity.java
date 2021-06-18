/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.app.Activity;
/*     */ import android.app.AlertDialog;
/*     */ import android.content.Context;
/*     */ import android.content.DialogInterface;
/*     */ import android.content.Intent;
/*     */ import android.graphics.Bitmap;
/*     */ import android.net.http.SslError;
/*     */ import android.os.AsyncTask;
/*     */ import android.os.Bundle;
/*     */ import android.util.Log;
/*     */ import android.webkit.JavascriptInterface;
/*     */ import android.webkit.JsResult;
/*     */ import android.webkit.SslErrorHandler;
/*     */ import android.webkit.WebChromeClient;
/*     */ import android.webkit.WebResourceError;
/*     */ import android.webkit.WebResourceRequest;
/*     */ import android.webkit.WebResourceResponse;
/*     */ import android.webkit.WebView;
/*     */ import android.webkit.WebViewClient;
/*     */ import android.widget.Toast;
/*     */ import com.android.volley.DefaultRetryPolicy;
/*     */ import com.android.volley.Request;
/*     */ import com.android.volley.RequestQueue;
/*     */ import com.android.volley.Response;
/*     */ import com.android.volley.RetryPolicy;
/*     */ import com.android.volley.VolleyError;
/*     */ import com.android.volley.toolbox.StringRequest;
/*     */ import com.android.volley.toolbox.Volley;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.URLEncoder;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import utility.RSAUtility;
/*     */ import utility.ServiceUtility;
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
/*     */ public class WebViewActivity
/*     */   extends Activity
/*     */ {
/*     */   Intent mainIntent;
/*     */   String html;
/*     */   String encVal;
/*     */   WebView webview;
/*     */   public static final String KEY_AC = "access_code";
/*     */   public static final String KEY_OID = "order_id";
/*     */   String vResponse;
/*     */   
/*     */   public void onCreate(Bundle bundle) {
/*  61 */     super.onCreate(bundle);
/*  62 */     setContentView(R.layout.activity_webview);
/*  63 */     this.mainIntent = getIntent();
/*  64 */     this.webview = (WebView)findViewById(R.id.webview);
/*  65 */     this.webview.setVisibility(0);
/*     */ 
/*     */     
/*  68 */     fetchInternet(this.mainIntent.getStringExtra("access_code"), this.mainIntent.getStringExtra("order_id"));
/*     */   }
/*     */   
/*     */   private class RenderView
/*     */     extends AsyncTask<Void, Void, Void>
/*     */   {
/*     */     private RenderView() {}
/*     */     
/*     */     protected void onPreExecute() {
/*  77 */       super.onPreExecute();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  83 */       LoadingDialog.showLoadingDialog((Context)WebViewActivity.this, "Please wait...");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected Void doInBackground(Void... arg0) {
/*  90 */       if (!ServiceUtility.chkNull(WebViewActivity.this.vResponse).equals("") && 
/*  91 */         ServiceUtility.chkNull(WebViewActivity.this.vResponse).toString().indexOf("ERROR") == -1) {
/*  92 */         StringBuffer vEncVal = new StringBuffer("");
/*  93 */         vEncVal.append(ServiceUtility.addToPostParams("cvv_number", WebViewActivity.this.mainIntent.getStringExtra("cvv_number")));
/*  94 */         vEncVal.append(ServiceUtility.addToPostParams("amount", WebViewActivity.this.mainIntent.getStringExtra("amount")));
/*  95 */         vEncVal.append(ServiceUtility.addToPostParams("currency", WebViewActivity.this.mainIntent.getStringExtra("currency")));
/*  96 */         vEncVal.append(ServiceUtility.addToPostParams("card_number", WebViewActivity.this.mainIntent.getStringExtra("card_number")));
/*  97 */         vEncVal.append(ServiceUtility.addToPostParams("customer_identifier", WebViewActivity.this.mainIntent.getStringExtra("customer_identifier")));
/*  98 */         vEncVal.append(ServiceUtility.addToPostParams("expiry_year", WebViewActivity.this.mainIntent.getStringExtra("expiry_year")));
/*  99 */         vEncVal.append(ServiceUtility.addToPostParams("expiry_month", WebViewActivity.this.mainIntent.getStringExtra("expiry_month")));
/* 100 */         WebViewActivity.this.encVal = RSAUtility.encrypt(vEncVal.substring(0, vEncVal.length() - 1), WebViewActivity.this.vResponse);
/* 101 */         Log.d("CCA ENCVAL", WebViewActivity.this.encVal);
/*     */       } 
/*     */       
/* 104 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void onPostExecute(Void result) {
/* 109 */       super.onPostExecute(result);
/*     */       
/* 111 */       LoadingDialog.cancelLoading();
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
/*     */       class MyJavaScriptInterface
/*     */       {
/*     */         @JavascriptInterface
/*     */         public void processHTML(String html)
/*     */         {
/* 134 */           String resp = html.split("\\{")[1].split("\\}")[0];
/*     */           
/* 136 */           resp = "{" + resp + "}";
/* 137 */           CustomModel.getInstance().changeState(resp);
/* 138 */           WebViewActivity.this.finish();
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       class MyJavaScriptInterface_new
/*     */       {
/*     */         @JavascriptInterface
/*     */         public void processHTML(String html)
/*     */         {
/* 150 */           if (html.contains("errorMessage")) {
/*     */             
/*     */             try {
/* 153 */               String resp = html.split("\\{")[1].split("\\}")[0];
/*     */               
/* 155 */               resp = "{" + resp + "}";
/* 156 */               JSONObject jo = new JSONObject(resp);
/*     */               
/* 158 */               Intent i = new Intent((Context)WebViewActivity.this, DialogActivity.class);
/*     */ 
/*     */               
/* 161 */               String msg = jo.getString("errorMessage");
/* 162 */               if (msg.contains("Required parameter missing")) {
/*     */                 
/* 164 */                 msg = msg.split("\\:")[1];
/* 165 */                 msg = "Required " + msg.replaceAll("_", " ");
/*     */               
/*     */               }
/* 168 */               else if (msg.contains("Invalid Parameter")) {
/*     */                 
/* 170 */                 msg = msg.split("\\:")[1];
/* 171 */                 msg = "Invalid " + msg.replaceAll("_", " ");
/*     */               } 
/* 173 */               i.putExtra("msg", msg);
/* 174 */               WebViewActivity.this.finish();
/* 175 */               WebViewActivity.this.startActivity(i);
/*     */             }
/* 177 */             catch (JSONException e) {
/* 178 */               e.printStackTrace();
/*     */             } 
/*     */           }
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 189 */       WebViewActivity.this.webview.getSettings().setJavaScriptEnabled(true);
/*     */       
/* 191 */       WebViewActivity.this.webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
/*     */       
/* 193 */       WebViewActivity.this.webview.addJavascriptInterface(new MyJavaScriptInterface_new(), "HTMLOUT_NEW");
/*     */       
/* 195 */       WebViewActivity.MyWebChromeClient myWebChromeClient = new WebViewActivity.MyWebChromeClient();
/* 196 */       WebViewActivity.this.webview.setWebChromeClient(myWebChromeClient);
/* 197 */       WebViewActivity.this.webview.setWebViewClient(new WebViewClient()
/*     */           {
/*     */             public void onPageFinished(WebView view, String url) {
/* 200 */               super.onPageFinished(WebViewActivity.this.webview, url);
/* 201 */               LoadingDialog.cancelLoading();
/* 202 */               if (url.equalsIgnoreCase(PaymentOptions.merchant.getRedirect_url())) {
/* 203 */                 WebViewActivity.this.webview.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
/* 204 */               } else if (url.indexOf("initTrans") != -1) {
/* 205 */                 WebViewActivity.this.webview.loadUrl("javascript:window.HTMLOUT_NEW.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
/*     */               } 
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
/* 214 */               Toast.makeText(WebViewActivity.this.getApplicationContext(), "Oh no! " + description, Toast.LENGTH_LONG).show();
                        LoadingDialog.cancelLoading();
/*     */             }
/*     */ 
/*     */             
/*     */             public void onPageStarted(WebView view, String url, Bitmap favicon) {
/* 219 */               super.onPageStarted(view, url, favicon);
/* 220 */               LoadingDialog.showLoadingDialog((Context)WebViewActivity.this, "Loading... "+url);
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
/* 226 */               super.onReceivedSslError(view, handler, error);
    Toast.makeText(WebViewActivity.this.getApplicationContext(), "Oh no! ssl" + error.toString(), Toast.LENGTH_LONG).show();
    LoadingDialog.cancelLoading();
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
/* 233 */               super.onReceivedError(view, request, error);
    Toast.makeText(WebViewActivity.this.getApplicationContext(), "Oh no! error" + error.getDescription(), Toast.LENGTH_LONG).show();
    LoadingDialog.cancelLoading();
/*     */             }
/*     */ 
/*     */             
/*     */             public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
/* 238 */               super.onReceivedHttpError(view, request, errorResponse);
    Toast.makeText(WebViewActivity.this.getApplicationContext(), "Oh no! error" + errorResponse.getReasonPhrase(), Toast.LENGTH_LONG).show();
    LoadingDialog.cancelLoading();
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 247 */         StringBuffer params = new StringBuffer();
/* 248 */         params.append(ServiceUtility.addToPostParams("access_code", WebViewActivity.this.mainIntent.getStringExtra("access_code")));
/* 249 */         params.append(ServiceUtility.addToPostParams("merchant_id", WebViewActivity.this.mainIntent.getStringExtra("merchant_id")));
        Log.d("CODES_WEBVIEW", "access_code : "+ WebViewActivity.this.mainIntent.getStringExtra("access_code") );
        Log.d("CODES_WEBVIEW", "merchant_id : "+ WebViewActivity.this.mainIntent.getStringExtra("merchant_id") );
        Log.d("CODES_WEBVIEW", "order_id : "+ WebViewActivity.this.mainIntent.getStringExtra("order_id") );


        /* 250 */         params.append(ServiceUtility.addToPostParams("order_id", WebViewActivity.this.mainIntent.getStringExtra("order_id")));
/* 251 */         params.append(ServiceUtility.addToPostParams("redirect_url", WebViewActivity.this.mainIntent.getStringExtra("redirect_url")));
/* 252 */         params.append(ServiceUtility.addToPostParams("cancel_url", WebViewActivity.this.mainIntent.getStringExtra("cancel_url")));
/* 253 */         params.append(ServiceUtility.addToPostParams("language", "EN"));
/* 254 */         params.append(ServiceUtility.addToPostParams("billing_name", WebViewActivity.this.mainIntent.getStringExtra("billing_name")));
/* 255 */         params.append(ServiceUtility.addToPostParams("billing_address", WebViewActivity.this.mainIntent.getStringExtra("billing_address")));
/* 256 */         params.append(ServiceUtility.addToPostParams("billing_city", WebViewActivity.this.mainIntent.getStringExtra("billing_city")));
/* 257 */         params.append(ServiceUtility.addToPostParams("billing_state", WebViewActivity.this.mainIntent.getStringExtra("billing_state")));
/*     */         
/* 259 */         params.append(ServiceUtility.addToPostParams("billing_country", WebViewActivity.this.mainIntent.getStringExtra("billing_country")));
/* 260 */         params.append(ServiceUtility.addToPostParams("billing_tel", WebViewActivity.this.mainIntent.getStringExtra("billing_tel")));
/* 261 */         params.append(ServiceUtility.addToPostParams("billing_email", WebViewActivity.this.mainIntent.getStringExtra("billing_email")));
/* 262 */         params.append(ServiceUtility.addToPostParams("delivery_name", WebViewActivity.this.mainIntent.getStringExtra("delivery_name")));
/* 263 */         params.append(ServiceUtility.addToPostParams("delivery_address", WebViewActivity.this.mainIntent.getStringExtra("delivery_address")));
/* 264 */         params.append(ServiceUtility.addToPostParams("delivery_city", WebViewActivity.this.mainIntent.getStringExtra("delivery_city")));
/* 265 */         params.append(ServiceUtility.addToPostParams("delivery_state", WebViewActivity.this.mainIntent.getStringExtra("delivery_state")));
/*     */         
/* 267 */         params.append(ServiceUtility.addToPostParams("delivery_country", WebViewActivity.this.mainIntent.getStringExtra("delivery_country")));
/* 268 */         params.append(ServiceUtility.addToPostParams("delivery_tel", WebViewActivity.this.mainIntent.getStringExtra("delivery_tel")));
/* 269 */         params.append(ServiceUtility.addToPostParams("merchant_param1", PaymentOptions.merchant.add1));
/* 270 */         params.append(ServiceUtility.addToPostParams("merchant_param2", PaymentOptions.merchant.add2));
/* 271 */         params.append(ServiceUtility.addToPostParams("merchant_param3", PaymentOptions.merchant.add3));
/* 272 */         params.append(ServiceUtility.addToPostParams("merchant_param4", PaymentOptions.merchant.add4));
/* 273 */         params.append(ServiceUtility.addToPostParams("merchant_param5", PaymentOptions.merchant.add5));
/* 274 */         params.append(ServiceUtility.addToPostParams("payment_option", WebViewActivity.this.mainIntent.getStringExtra("payment_option")));
/* 275 */         params.append(ServiceUtility.addToPostParams("card_type", WebViewActivity.this.mainIntent.getStringExtra("card_type")));
/* 276 */         params.append(ServiceUtility.addToPostParams("card_name", WebViewActivity.this.mainIntent.getStringExtra("card_name")));
/* 277 */         params.append(ServiceUtility.addToPostParams("data_accept", WebViewActivity.this.mainIntent.getStringExtra("data_accept")));
/* 278 */         params.append(ServiceUtility.addToPostParams("enc_val", URLEncoder.encode(WebViewActivity.this.encVal, "UTF-8")));
/* 279 */         params.append(ServiceUtility.addToPostParams("emi_plan_id", WebViewActivity.this.mainIntent.getStringExtra("emi_plan_id")));
/* 280 */         params.append(ServiceUtility.addToPostParams("emi_tenure_id", WebViewActivity.this.mainIntent.getStringExtra("emi_tenure_id")));
/* 281 */         params.append(ServiceUtility.addToPostParams("response_type", "JSON"));
/* 282 */         params.append(ServiceUtility.addToPostParams("integration_type", "payload"));
/*     */ 
/*     */         
/* 285 */         if (WebViewActivity.this.mainIntent.hasExtra("si_start_date")) {
/* 286 */           params.append(ServiceUtility.addToPostParams("si_type", WebViewActivity.this.mainIntent.getStringExtra("si_type")));
/* 287 */           params.append(ServiceUtility.addToPostParams("si_mer_ref_no", WebViewActivity.this.mainIntent.getStringExtra("si_mer_ref_no")));
/* 288 */           params.append(ServiceUtility.addToPostParams("si_is_setup_amt", WebViewActivity.this.mainIntent.getStringExtra("si_is_setup_amt")));
/* 289 */           params.append(ServiceUtility.addToPostParams("si_amount", WebViewActivity.this.mainIntent.getStringExtra("si_amount")));
/* 290 */           params.append(ServiceUtility.addToPostParams("si_start_date", WebViewActivity.this.mainIntent.getStringExtra("si_start_date")));
/* 291 */           params.append(ServiceUtility.addToPostParams("si_frequency", WebViewActivity.this.mainIntent.getStringExtra("si_frequency")));
/* 292 */           params.append(ServiceUtility.addToPostParams("si_frequency_type", WebViewActivity.this.mainIntent.getStringExtra("si_frequency_type")));
/* 293 */           params.append(ServiceUtility.addToPostParams("si_bill_cycle", WebViewActivity.this.mainIntent.getStringExtra("si_bill_cycle")));
/*     */         } 
/*     */         
/* 296 */         if (WebViewActivity.this.mainIntent.getStringExtra("saveCard") != null) {
/* 297 */           params.append(ServiceUtility.addToPostParams("saveCard", WebViewActivity.this.mainIntent.getStringExtra("saveCard")));
/*     */         }
/*     */         
/* 300 */         if (WebViewActivity.this.mainIntent.getStringExtra("promo_code") != null) {
/* 301 */           params.append(ServiceUtility.addToPostParams("promo_code", WebViewActivity.this.mainIntent.getStringExtra("promo_code")));
/*     */         }
/*     */ 
/*     */         
/* 305 */         String vPostParams = params.substring(0, params.length() - 1);
/* 306 */         WebViewActivity.this.webview.postUrl("https://secure.ccavenue.ae/transaction/initTrans", vPostParams.getBytes());
/* 307 */       } catch (UnsupportedEncodingException e) {
/* 308 */         e.printStackTrace();
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public void showToast(String msg) {
/* 314 */     Toast.makeText((Context)this, "Toast: " + msg, 1).show();
/*     */   }
/*     */   
/*     */   public class MyWebChromeClient
/*     */     extends WebChromeClient
/*     */   {
/*     */     public boolean onJsAlert(WebView view, String url, String message, JsResult jsResult) {
/* 321 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void fetchInternet(final String ac, final String od) {
/* 327 */     LoadingDialog.showLoadingDialog((Context)this, "Loading...");
/*     */     
/* 329 */     StringRequest stringRequest = new StringRequest(Request.Method.GET, this.mainIntent.getStringExtra("rsa_key_url"), new Response.Listener<String>()
/*     */         {
/*     */           
/*     */           public void onResponse(String response)
/*     */           {
/* 334 */             LoadingDialog.cancelLoading();
/* 335 */             Log.d("CCA RSA", response);
/* 336 */             WebViewActivity.this.vResponse = response;
/* 337 */             (new WebViewActivity.RenderView()).execute();
/*     */           }
/*     */         },new Response.ErrorListener()
/*     */         {
/*     */           public void onErrorResponse(VolleyError error)
/*     */           {
/* 343 */             LoadingDialog.cancelLoading();
/*     */           }
/*     */         })
/*     */       {
/*     */         protected Map<String, String> getParams()
/*     */         {
/* 349 */           Map<String, String> params = new HashMap<>();
/* 350 */           params.put("access_code", ac);
/* 351 */           params.put("order_id", od);
/* 352 */           return params;
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 357 */     stringRequest.setRetryPolicy((RetryPolicy)new DefaultRetryPolicy(0, -1, 1.0F));
/* 358 */     RequestQueue requestQueue = Volley.newRequestQueue((Context)this);
/* 359 */     requestQueue.add((Request)stringRequest);
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
/*     */   public void onBackPressed() {
/* 371 */     AlertDialog alertDialog = (new AlertDialog.Builder((Context)this)).create();
/*     */ 
/*     */     
/* 374 */     alertDialog.setMessage("Do you really want to cancel this transaction?");
/*     */     
/* 376 */     alertDialog.setButton(-2, "CANCEL", new DialogInterface.OnClickListener()
/*     */         {
/*     */           public void onClick(DialogInterface dialog, int which) {}
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 383 */     alertDialog.setButton(-1, "OK", new DialogInterface.OnClickListener()
/*     */         {
/*     */           public void onClick(DialogInterface dialog, int which)
/*     */           {
/* 387 */             CustomModel.getInstance().changeState("Transaction Closed");
/* 388 */             WebViewActivity.this.finish();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 393 */     alertDialog.show();
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/WebViewActivity.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */