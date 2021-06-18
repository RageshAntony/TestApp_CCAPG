/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import retrofit2.Call;
/*    */ import retrofit2.Converter;
/*    */ import retrofit2.Retrofit;
/*    */ import retrofit2.converter.gson.GsonConverterFactory;
/*    */ import retrofit2.http.Body;
/*    */ import retrofit2.http.Headers;
/*    */ import retrofit2.http.POST;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RetrofitClient
/*    */ {
/*    */   private static GitApiInterface gitApiInterface;
/*    */   private static GitApiInterface1 gitApiInterface1;
/* 23 */   private static String PNR_URL = "https://secure.ccavenue.ae/";
/* 24 */   private static String PNR_URL1 = "https://login.ccavenue.ae/";
/*    */   
/*    */   public static GitApiInterface Indianrail() {
/* 27 */     if (gitApiInterface == null) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 34 */       Retrofit client = (new Retrofit.Builder()).baseUrl(PNR_URL).addConverterFactory(new ToStringConverterFactory()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).build();
/* 35 */       gitApiInterface = (GitApiInterface)client.create(GitApiInterface.class);
/*    */     } 
/* 37 */     return gitApiInterface;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static GitApiInterface1 Indianrail1() {
/* 43 */     if (gitApiInterface1 == null) {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 50 */       Retrofit client = (new Retrofit.Builder()).baseUrl(PNR_URL1).addConverterFactory(new ToStringConverterFactory()).addConverterFactory((Converter.Factory)GsonConverterFactory.create()).build();
/* 51 */       gitApiInterface1 = (GitApiInterface1)client.create(GitApiInterface1.class);
/*    */     } 
/* 53 */     return gitApiInterface1;
/*    */   }
/*    */   
/*    */   public static interface GitApiInterface1 {
/*    */     @Headers({"Content-Type: application/json", "User-Agent: Mobile"})
/*    */     @POST("appapi")
/*    */     Call<String> get_color(@Body String param1String);
/*    */   }
/*    */   
/*    */   public static interface GitApiInterface {
/*    */     @Headers({"Content-Type: application/json", "User-Agent: Mobile"})
/*    */     @POST("transaction/appapi.do?command=getMerchantSetting")
/*    */     Call<String> post_setting(@Body String param1String);
/*    */     
/*    */     @Headers({"Content-Type: application/json", "User-Agent: Mobile"})
/*    */     @POST("transaction/appapi.do?command=getPromotionsListing")
/*    */     Call<String> post_listing(@Body String param1String);
/*    */     
/*    */     @Headers({"Content-Type: application/json", "User-Agent: Mobile"})
/*    */     @POST("transaction/appapi.do?command=getPromotionsApply")
/*    */     Call<String> post_apply(@Body String param1String);
/*    */     
/*    */     @Headers({"Content-Type: application/json", "User-Agent: Mobile"})
/*    */     @POST("/transaction/appapi.do?command=getDiscountApply")
/*    */     Call<String> post_apply_coupon(@Body String param1String);
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/RetrofitClient.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */