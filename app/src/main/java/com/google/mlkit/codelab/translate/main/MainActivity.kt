/*
 * Copyright 2019 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.google.mlkit.codelab.translate.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.mlkit.codelab.translate.R
import com.google.mlkit.codelab.translate.SelectFragment

class MainActivity : AppCompatActivity(), SelectFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            onSelectFragment(MainFragment.newInstance(), false)
        }
    }

    override fun onSelectFragment(fragment: Fragment, isAddToBackStack: Boolean) {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container, fragment)
            if (!isAddToBackStack) {
                commitNow()
            } else {
                addToBackStack(null)
                commit()
            }
        }
    }

}