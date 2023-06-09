/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.raywenderlich.rwnews.di

import com.raywenderlich.rwnews.ui.detail.NewsDetailPresenter
import com.raywenderlich.rwnews.ui.detail.NewsDetailPresenterImpl
import com.raywenderlich.rwnews.ui.detail.NewsDetailViewBinder
import com.raywenderlich.rwnews.ui.detail.NewsDetailViewBinderImpl
import com.raywenderlich.rwnews.ui.list.NewsListPresenter
import com.raywenderlich.rwnews.ui.list.NewsListPresenterImpl
import com.raywenderlich.rwnews.ui.list.NewsListViewBinder
import com.raywenderlich.rwnews.ui.list.NewsListViewBinderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@Module
@InstallIn(FragmentComponent::class) // attached to the fragment scope lifecycle
interface FeatureModule {

  // all of this implementation uses @FragmentScoped
  //scope annotation for bindings that should exist for the life of a fragment.

  // so like singletons for fragments
  // the same instance as long as the instance that created it is alive

  // if another instance of fragment gets created receives a new instance injected but will be the same
  // for that instance as long keeps alive
  @Binds
  fun bindNewsListPresenter(impl: NewsListPresenterImpl): NewsListPresenter

  @Binds
  fun bindNewsListViewBinder(impl: NewsListViewBinderImpl): NewsListViewBinder

  @Binds
  fun bindNewsDetailPresenter(impl: NewsDetailPresenterImpl): NewsDetailPresenter

  @Binds
  fun bindNewsDetailViewBinder(impl: NewsDetailViewBinderImpl): NewsDetailViewBinder
}