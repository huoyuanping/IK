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
 */
package org.wltea.analyzer.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * IK鍒嗚瘝鍣紝Lucene Analyzer鎺ュ彛瀹炵幇
 * 鍏煎Lucene 4.0鐗堟湰
 */
public final class IKAnalyzer extends Analyzer {

  private boolean useSmart;

  public boolean useSmart() {
    return useSmart;
  }

  public void setUseSmart(boolean useSmart) {
    this.useSmart = useSmart;
  }

  /**
   * IK鍒嗚瘝鍣↙ucene  Analyzer鎺ュ彛瀹炵幇绫�
   *
   * 榛樿缁嗙矑搴﹀垏鍒嗙畻娉�
   */
  public IKAnalyzer() {
    this(false);
  }

  /**
   * IK鍒嗚瘝鍣↙ucene Analyzer鎺ュ彛瀹炵幇绫�
   *
   * @param useSmart 褰撲负true鏃讹紝鍒嗚瘝鍣ㄨ繘琛屾櫤鑳藉垏鍒�
   */
  public IKAnalyzer(boolean useSmart) {
    super();
    this.useSmart = useSmart;
  }

  /**
   * 閲嶈浇Analyzer鎺ュ彛锛屾瀯閫犲垎璇嶇粍浠�
   */
  @Override
  protected TokenStreamComponents createComponents(String fieldName) {
    Tokenizer _IKTokenizer = new IKTokenizer(this.useSmart());
    return new TokenStreamComponents(_IKTokenizer);
  }

}
