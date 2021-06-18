/*     */ package mumbai.dev.sdkdubai;
/*     */ 
/*     */ import android.content.Context;
/*     */ import android.graphics.Canvas;
/*     */ import android.graphics.drawable.Drawable;
/*     */ import android.util.AttributeSet;
/*     */ import android.util.SparseArray;
/*     */ import android.widget.Toast;
/*     */ import androidx.appcompat.widget.AppCompatEditText;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import mumbai.dev.sdkdubai.dto.Promotion;
/*     */ import retrofit2.Call;
/*     */ import retrofit2.Callback;
/*     */ import retrofit2.Response;
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
/*     */ public class CreditCardEditText
/*     */   extends AppCompatEditText
/*     */ {
/*  31 */   private SparseArray<Pattern> mCCPatterns = null;
/*     */   onAddTextViewCustomListener onAddTextViewCustomListener;
/*  33 */   private final char mSeparator = ' ';
/*  34 */   public static int selected_card = 0;
/*     */   
/*  36 */   private final int mDefaultDrawableResId = R.drawable.unknown_cc;
/*  37 */   private int mCurrentDrawableResId = 0;
/*     */   private Drawable mCurrentDrawable;
/*     */   Context ctx;
/*     */   
/*     */   public CreditCardEditText(Context context) {
/*  42 */     super(context);
/*  43 */     this.ctx = context;
/*  44 */     init();
/*     */   }
/*     */   
/*     */   public CreditCardEditText(Context context, AttributeSet attrs) {
/*  48 */     super(context, attrs);
/*  49 */     this.ctx = context;
/*  50 */     init();
/*     */   }
/*     */   
/*     */   public CreditCardEditText(Context context, AttributeSet attrs, int defStyleAttr) {
/*  54 */     super(context, attrs, defStyleAttr);
/*  55 */     this.ctx = context;
/*  56 */     init();
/*     */   }
/*     */   
/*     */   private void init() {
/*  60 */     if (this.mCCPatterns == null) {
/*  61 */       this.mCCPatterns = new SparseArray();
/*     */       
/*  63 */       this.mCCPatterns.put(R.drawable.visa, Pattern.compile("^4[0-9]{2,12}(?:[0-9]{3})?$"));
/*  64 */       this.mCCPatterns.put(R.drawable.master, Pattern.compile("^5[1-5][0-9]{1,14}$"));
/*  65 */       this.mCCPatterns.put(R.drawable.amex, Pattern.compile("^3[47][0-9]{1,13}$"));
/*  66 */       this.mCCPatterns.put(R.drawable.discover, Pattern.compile("^6(?:011|5[0-9]{2})[0-9]{1,12}$"));
/*  67 */       this.mCCPatterns.put(R.drawable.dinersclub, Pattern.compile("^3(?:0[0-5]|[68][0-9])[0-9]{1,11}$"));
/*  68 */       this.mCCPatterns.put(R.drawable.jcb, Pattern.compile("^35[0-9]{1,14}$"));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
/*  77 */     if (this.mCCPatterns == null) {
/*  78 */       init();
/*     */     }
/*     */     
/*  81 */     int mDrawableResId = 0;
/*  82 */     for (int i = 0; i < this.mCCPatterns.size(); i++) {
/*  83 */       int key = this.mCCPatterns.keyAt(i);
/*     */       
/*  85 */       Pattern p = (Pattern)this.mCCPatterns.get(key);
/*     */       
/*  87 */       Matcher m = p.matcher(text);
/*  88 */       if (m.find()) {
/*  89 */         mDrawableResId = key;
/*  90 */         selected_card = key;
/*     */         break;
/*     */       } 
/*     */     } 
/*  94 */     if (mDrawableResId > 0 && mDrawableResId != this.mCurrentDrawableResId) {
/*  95 */       this.mCurrentDrawableResId = mDrawableResId;
/*  96 */     } else if (mDrawableResId == 0) {
/*  97 */       this.mCurrentDrawableResId = R.drawable.unknown_cc;
/*     */     } 
/*     */     
/* 100 */     this.mCurrentDrawable = getResources().getDrawable(this.mCurrentDrawableResId);
/*     */     
/* 102 */     if (text.length() == 16) {
/*     */       
/* 104 */       if (PaymentOptions.pos > 0)
/* 105 */         if (!PaymentOptions.Check(text.toString())) {
/* 106 */           Toast.makeText(getContext(), "Please enter valid credit card number", 1).show();
/*     */         }
/*     */         else {
/*     */           
/* 110 */           apply_promo(text.toString());
/*     */         }  
/* 112 */     } else if (text.length() > 0) {
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void onDraw(Canvas canvas) {
/* 122 */     super.onDraw(canvas);
/* 123 */     if (this.mCurrentDrawable == null) {
/*     */       return;
/*     */     }
/*     */     
/* 127 */     int rightOffset = 0;
/* 128 */     if (getError() != null && getError().length() > 0) {
/* 129 */       rightOffset = (int)(getResources().getDisplayMetrics()).density * 32;
/*     */     }
/*     */     
/* 132 */     int right = getWidth() - getPaddingRight() - rightOffset;
/*     */     
/* 134 */     int top = getPaddingTop();
/* 135 */     int bottom = getHeight() - getPaddingBottom();
/* 136 */     float ratio = this.mCurrentDrawable.getIntrinsicWidth() / this.mCurrentDrawable.getIntrinsicHeight();
/*     */     
/* 138 */     int left = (int)(right - (bottom - top) * ratio);
/* 139 */     this.mCurrentDrawable.setBounds(left, top, right, bottom);
/*     */     
/* 141 */     this.mCurrentDrawable.draw(canvas);
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
/*     */   public void remove_promo() {
/* 153 */     this.onAddTextViewCustomListener = (onAddTextViewCustomListener)this.ctx;
/* 154 */     this.onAddTextViewCustomListener.onAddText("remove");
/*     */   }
/*     */   
/*     */   public void apply_promo(String cardno) {
/* 158 */     LoadingDialog.showLoadingDialog(getContext(), "Loading....");
/* 159 */     RetrofitClient.GitApiInterface service = RetrofitClient.Indianrail();
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
/* 175 */     String body = "{\n\t\"promoCode\":\"" + ((Promotion)PaymentOptions.promolist.get(PaymentOptions.pos - 1)).getPromoId() + "\"\n\t,\"currency\":\"" + PaymentOptions.merchant.getCurrency() + "\"\n\t,\"amount\":\"" + PaymentOptions.merchant.getAmount() + "\"\n\t,\"merchant_id\":\"" + PaymentOptions.merchant.getMerchant_id() + "\"\n\t,\"pay_opt_type\":\"\"\n\t,\"settingPromotions\":\"Y\"\n\t,\"cardType\":\"\"\n\t,\"cardName\":\"\"\n\t,\"cardNumber\":\"" + cardno + "\"\n\t,\"customerIdVault\":\"\"\n\t,\"emailId\":\"" + PaymentOptions.billing.getEmail() + "\"\n}";
/*     */     
/* 177 */     Call<String> call = service.post_apply(body);
/*     */     
/* 179 */     call.enqueue(new Callback<String>()
/*     */         {
/*     */           public void onResponse(Call<String> call, Response<String> response)
/*     */           {
/*     */             try {
/* 184 */               LoadingDialog.cancelLoading();
/*     */               
/* 186 */               if (response.body() != null) {
/* 187 */                 CreditCardEditText.this.onAddTextViewCustomListener = (onAddTextViewCustomListener)CreditCardEditText.this.ctx;
/* 188 */                 CreditCardEditText.this.onAddTextViewCustomListener.onAddText((String)response.body());
/*     */               } else {
/*     */                 
/* 191 */                 Toast.makeText(CreditCardEditText.this.getContext(), "No Response. Please try Again!!!", 1).show();
/*     */               
/*     */               }
/*     */             
/*     */             }
/* 196 */             catch (Exception e) {
/*     */               
/* 198 */               Toast.makeText(CreditCardEditText.this.getContext(), "Error. Please try Again!!!", 1).show();
/* 199 */               LoadingDialog.cancelLoading();
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void onFailure(Call<String> call, Throwable t) {
/* 207 */             if (t instanceof java.net.SocketTimeoutException) {
/* 208 */               LoadingDialog.cancelLoading();
/* 209 */               Toast.makeText(CreditCardEditText.this.getContext(), "Something went wrong. Please try Again!!!", 1).show();
/*     */             } else {
/*     */               
/* 212 */               LoadingDialog.cancelLoading();
/* 213 */               Toast.makeText(CreditCardEditText.this.getContext(), "Something went wrong. Please try Again!!!", 1).show();
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/CreditCardEditText.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */