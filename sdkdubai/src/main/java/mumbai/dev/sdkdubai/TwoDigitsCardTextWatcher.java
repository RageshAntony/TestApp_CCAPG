/*    */ package mumbai.dev.sdkdubai;
/*    */ 
/*    */ import android.text.Editable;
/*    */ import android.text.TextWatcher;
/*    */ import android.widget.EditText;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TwoDigitsCardTextWatcher
/*    */   implements TextWatcher
/*    */ {
/*    */   private static final String INITIAL_MONTH_ADD_ON = "0";
/*    */   private static final String DEFAULT_MONTH = "01";
/*    */   private static final String SPACE = "/";
/*    */   private EditText mEditText;
/*    */   private int mLength;
/*    */   
/*    */   public TwoDigitsCardTextWatcher(EditText editText) {
/* 19 */     this.mEditText = editText;
/*    */   }
/*    */ 
/*    */   
/*    */   public void onTextChanged(CharSequence s, int start, int before, int count) {
/* 24 */     this.mEditText.setError(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void beforeTextChanged(CharSequence s, int start, int count, int after) {
/* 29 */     this.mLength = this.mEditText.getText().length();
/*    */   }
/*    */ 
/*    */   
/*    */   public void afterTextChanged(Editable s) {
/* 34 */     int currentLength = this.mEditText.getText().length();
/* 35 */     boolean ignoreBecauseIsDeleting = false;
/* 36 */     if (this.mLength > currentLength) {
/* 37 */       ignoreBecauseIsDeleting = true;
/*    */     }
/*    */     
/* 40 */     if (ignoreBecauseIsDeleting && s.toString().startsWith("/")) {
/*    */       return;
/*    */     }
/*    */     
/* 44 */     if (s.length() == 1 && !isNumberChar(String.valueOf(s.charAt(0)))) {
/* 45 */       this.mEditText.setText("01/");
/* 46 */     } else if (s.length() == 1 && !isCharValidMonth(s.charAt(0))) {
/* 47 */       this.mEditText.setText("0" + String.valueOf(s.charAt(0)) + "/");
/* 48 */     } else if (s.length() == 2 && String.valueOf(s.charAt(s.length() - 1)).equals("/")) {
/* 49 */       this.mEditText.setText("0" + String.valueOf(s));
/* 50 */     } else if (!ignoreBecauseIsDeleting && s
/* 51 */       .length() == 2 && !String.valueOf(s.charAt(s.length() - 1)).equals("/")) {
/* 52 */       this.mEditText.setText(this.mEditText.getText().toString() + "/");
/* 53 */     } else if (s.length() == 3 && !String.valueOf(s.charAt(s.length() - 1)).equals("/") && !ignoreBecauseIsDeleting) {
/* 54 */       s.insert(2, "/");
/* 55 */       this.mEditText.setText(String.valueOf(s));
/* 56 */     } else if (s.length() > 3 && !isCharValidMonth(s.charAt(0))) {
/* 57 */       this.mEditText.setText("0" + s);
/*    */     } 
/*    */     
/* 60 */     if (!ignoreBecauseIsDeleting) {
/* 61 */       this.mEditText.setSelection(this.mEditText.getText().toString().length());
/*    */     }
/*    */   }
/*    */   
/*    */   private boolean isCharValidMonth(char charFromString) {
/* 66 */     int month = 0;
/* 67 */     if (Character.isDigit(charFromString)) {
/* 68 */       month = Integer.parseInt(String.valueOf(charFromString));
/*    */     }
/* 70 */     return (month <= 1);
/*    */   }
/*    */   
/*    */   private boolean isNumberChar(String string) {
/* 74 */     return string.matches(".*\\d.*");
/*    */   }
/*    */ }


/* Location:              /Users/radicalstart/Documents/ragesh/android/TestApp SI/app/libs/sdkdubai-release.aar!/classes.jar!/mumbai/dev/sdkdubai/TwoDigitsCardTextWatcher.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */