package com.mehmetbalbay.bitcointicker.view.ui.mycoins

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.mehmetbalbay.bitcointicker.R
import com.mehmetbalbay.bitcointicker.base.ViewModelFragment
import com.mehmetbalbay.bitcointicker.databinding.FragmentMyCoinsBinding
import com.mehmetbalbay.bitcointicker.models.entity.CoinDetailItem
import com.mehmetbalbay.bitcointicker.models.network.Description
import com.mehmetbalbay.bitcointicker.models.network.Image
import com.mehmetbalbay.bitcointicker.utils.Const
import com.mehmetbalbay.bitcointicker.view.adapter.mycoins.MyCoinsAdapter
import com.mehmetbalbay.bitcointicker.view.ui.auth.AuthViewModel
import com.mehmetbalbay.bitcointicker.view.ui.main.MainActivityViewModel
import com.mehmetbalbay.bitcointicker.view.ui.viewholder.mycoins.MyCoinsViewHolder
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MyCoinsFragment : ViewModelFragment(), MyCoinsListener, MyCoinsViewHolder.Delegate {

    private val viewModel: MainActivityViewModel by injectActivityViewModels()
    private val authViewModel: AuthViewModel by injectViewModels()
    private lateinit var binding: FragmentMyCoinsBinding

    private val firebaseStore: FirebaseFirestore by lazy {
        FirebaseFirestore.getInstance()
    }

    lateinit var myCoinAdapter: MyCoinsAdapter

    private val disposables = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding<FragmentMyCoinsBinding>(
            inflater, R.layout.fragment_my_coins, container
        ).apply {
            binding = this
            viewModel = this@MyCoinsFragment.viewModel
            lifecycleOwner = this@MyCoinsFragment
            myCoinsAdapter = MyCoinsAdapter(this@MyCoinsFragment)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbarTitle(binding.toolbar.title, getString(R.string.my_coins_fragment))

        val firebaseUser = authViewModel.user
        firebaseUser?.let {
            subscribeGetMyCoinFavoriteList(firebaseUser)
        }

        myCoinAdapter = MyCoinsAdapter(this)
        initializeUI()
    }

    private fun subscribeGetMyCoinFavoriteList(firebaseUser: FirebaseUser) {
        val disposable = getMyCoinFavoriteList(firebaseUser)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {
                it.message?.run {
                }
            })
        disposables.add(disposable)
    }

    private fun getMyCoinFavoriteList(firebaseUser: FirebaseUser) = Completable.create { emitter ->
        val documentRefFavorite =
            firebaseStore.collection(Const.myCoinCollectionName).document(firebaseUser.uid)
        val collectionMyFavorite = documentRefFavorite.collection(Const.myFavoriteList)
        if (!emitter.isDisposed) {

            val noteListener =
                collectionMyFavorite.addSnapshotListener { value, error ->
                    if (error != null) {

                    }

                    if (!value?.documents.isNullOrEmpty()) {
                        val coinDetailItemList = ArrayList<CoinDetailItem>()
                        value?.let {
                            for (documentSnapshot in value.documents) {
                                val dataHashMap =
                                    documentSnapshot.data?.get(Const.coinDetailItem) as HashMap<*, *>?
                                val image = dataHashMap?.get(Const.coinImage) as HashMap<*, *>?
                                val id: String = (dataHashMap?.get(Const.coinId) ?: "id") as String
                                val symbol = dataHashMap?.get(Const.coinSymbol) as String?
                                val name = dataHashMap?.get(Const.coinName) as String?
                                val hashingAlgorithm =
                                    dataHashMap?.get("hashingAlgorithm") as String?
                                val favorite = dataHashMap?.get(Const.coinFavorite) as Boolean?
                                val description =
                                    dataHashMap?.get(Const.coinDescription) as HashMap<*, *>?


                                val marketData = dataHashMap?.get(Const.coinImage) as HashMap<*, *>?

                                val descriptionTr = description?.get("tr") as String?

                                val smallImageUrl = image?.get("small")

                                val thumb = image?.get("thumb") as String?
                                val large = image?.get("large") as String?
                                val small = image?.get("small") as String?
                                val imageModel = Image(thumb ?: "", small ?: "", large ?: "")
                                val descriptionModel = Description(
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    tr = descriptionTr ?: "",
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null
                                )
                                val coinDetailItem = CoinDetailItem(
                                    id,
                                    symbol,
                                    name,
                                    imageModel,
                                    null,
                                    hashingAlgorithm,
                                    descriptionModel,
                                    favorite
                                )
                                coinDetailItemList.add(coinDetailItem)
                            }
                            myCoinAdapter.setData(coinDetailItemList)
                            binding.myCoinsRecyclerview.adapter = myCoinAdapter
                        }
                    }
                }
        }
    }

    private fun initializeUI() {

    }

    override fun onItemClick(coinDetailItem: CoinDetailItem, view: View) {

    }

    override fun onStarted() {

    }

    override fun onSuccess() {

    }

    override fun onFailure(message: String) {

    }

}