/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.lang.annotation.Annotation;
/*    */ import java.lang.reflect.Type;
/*    */ import okhttp3.MediaType;
/*    */ import okhttp3.RequestBody;
/*    */ import okhttp3.ResponseBody;
/*    */ import retrofit2.Converter;
/*    */ import retrofit2.Retrofit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ToStringConverterFactory
/*    */   extends Converter.Factory
/*    */ {
/* 18 */   private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");
/*    */ 
/*    */ 
/*    */   
/*    */   public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
/* 23 */     if (String.class.equals(type)) {
/* 24 */       return new Converter<ResponseBody, String>()
/*    */         {
/*    */           public String convert(ResponseBody value) throws IOException {
/* 27 */             return value.string();
/*    */           }
/*    */         };
/*    */     }
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
/* 38 */     if (String.class.equals(type)) {
/* 39 */       return new Converter<String, RequestBody>()
/*    */         {
/*    */           public RequestBody convert(String value) throws IOException {
/* 42 */             return RequestBody.create(ToStringConverterFactory.MEDIA_TYPE, value);
/*    */           }
/*    */         };
/*    */     }
/* 46 */     return null;
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/ToStringConverterFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */