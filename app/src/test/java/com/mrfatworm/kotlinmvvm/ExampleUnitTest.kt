package com.mrfatworm.kotlinmvvm

import com.mrfatworm.kotlinmvvm.mvp.IMvp
import com.mrfatworm.kotlinmvvm.mvp.MvpActivityPresenter
import io.mockk.*
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var list: List<Int>
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }

    @Before
    fun setup(){
        list = listOf(0, 1, 2, 5, 7, 100)
    }

    @Test
    fun testSize(){
        assertEquals(6, list.size)
    }

    @Test
    fun testIndex(){
        assertEquals(100, list[5])
    }

    @After
    fun tearDown(){
        // TODO Remove resource
    }

    @Test
    fun testRequestUserName(){
        val view = mockk<IMvp>()//假造一個IView的空實體
        val presenter = MvpActivityPresenter(view)//我們實際測試的部份

        //當假造的IView被呼叫到receivedUserName()，就讓它執行下去runs。
        every {
            view.receiveName(any())
        } just Runs

        //我們針對實際測試的部份(presenter)呼叫測試function
        presenter.requestUserName()

        //驗證IView在presenter.requestUserName()後是否有被呼叫receivedUserName()
        verify {
            view.receiveName(any())
        }
    }
}