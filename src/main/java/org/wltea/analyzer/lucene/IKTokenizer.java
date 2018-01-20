/**
 * IK 涓枃鍒嗚瘝  鐗堟湰 5.0.1
 * IK Analyzer release 5.0.1
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 婧愪唬鐮佺敱鏋楄壇鐩�linliangyi2005@gmail.com)鎻愪緵
 * 鐗堟潈澹版槑 2012锛屼箤榫欒尪宸ヤ綔瀹�
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 

 * 
 */
package org.wltea.analyzer.lucene;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

/**
 * IK鍒嗚瘝鍣�Lucene Tokenizer閫傞厤鍣ㄧ被
 * 鍏煎Lucene 4.0鐗堟湰
 */
public final class IKTokenizer extends Tokenizer {

  // IK鍒嗚瘝鍣ㄥ疄鐜�
  private IKSegmenter _IKImplement;

  // 璇嶅厓鏂囨湰灞炴�
  private final CharTermAttribute termAtt;
  // 璇嶅厓浣嶇Щ灞炴�
  private final OffsetAttribute offsetAtt;
  // 璇嶅厓鍒嗙被灞炴�锛堣灞炴�鍒嗙被鍙傝�org.wltea.analyzer.core.Lexeme涓殑鍒嗙被甯搁噺锛�
  private final TypeAttribute typeAtt;
  // 璁板綍鏈�悗涓�釜璇嶅厓鐨勭粨鏉熶綅缃�
  private int endPosition;

  /**
   * Lucene 4.0 Tokenizer閫傞厤鍣ㄧ被鏋勯�鍑芥暟
   * @param in
   * @param useSmart
   */
  public IKTokenizer( boolean useSmart) {
    super();
    offsetAtt = addAttribute(OffsetAttribute.class);
    termAtt = addAttribute(CharTermAttribute.class);
    typeAtt = addAttribute(TypeAttribute.class);
    _IKImplement = new IKSegmenter(input, useSmart);
  }

  /*
   * (non-Javadoc)
   * @see org.apache.lucene.analysis.TokenStream#incrementToken()
   */
  @Override
  public boolean incrementToken() throws IOException {
    // 娓呴櫎鎵�湁鐨勮瘝鍏冨睘鎬�
    clearAttributes();
    Lexeme nextLexeme = _IKImplement.next();
    if (nextLexeme != null) {
      // 灏哃exeme杞垚Attributes
      // 璁剧疆璇嶅厓鏂囨湰
      termAtt.append(nextLexeme.getLexemeText());
      // 璁剧疆璇嶅厓闀垮害
      termAtt.setLength(nextLexeme.getLength());
      // 璁剧疆璇嶅厓浣嶇Щ
      offsetAtt.setOffset(nextLexeme.getBeginPosition(), nextLexeme.getEndPosition());
      // 璁板綍鍒嗚瘝鐨勬渶鍚庝綅缃�
      endPosition = nextLexeme.getEndPosition();
      // 璁板綍璇嶅厓鍒嗙被
      typeAtt.setType(nextLexeme.getLexemeTypeString());
      // 杩斾細true鍛婄煡杩樻湁涓嬩釜璇嶅厓
      return true;
    }
    // 杩斾細false鍛婄煡璇嶅厓杈撳嚭瀹屾瘯
    return false;
  }

  /*
   * (non-Javadoc)
   * @see org.apache.lucene.analysis.Tokenizer#reset(java.io.Reader)
   */
  @Override
  public void reset() throws IOException {
    super.reset();
    _IKImplement.reset(input);
  }

  @Override
  public final void end() {
    // set final offset
    int finalOffset = correctOffset(this.endPosition);
    offsetAtt.setOffset(finalOffset, finalOffset);
  }
}
