package com.merttoptas.composebase.domain.viewstate

/**
 * Created by merttoptas on 12.03.2022
 */

interface IViewState

interface IViewEvent

/*
//This code will be used when UseCase layer is created.

sealed class ViewEventWrapper<T : IViewEvent> {
    class LoadInitialData<T : IViewEvent> : ViewEventWrapper<T>()
    class PageEvent<T : IViewEvent>(val pageEvent: T) : ViewEventWrapper<T>()
}

sealed class ViewData<ViewState : IViewState, SubViewEvent : IViewEvent> {
    class State<ViewState : IViewState, SubViewEvent : IViewEvent>(val data: ViewState) :
        ViewData<ViewState, SubViewEvent>()

    class Event<ViewState : IViewState, SubViewEvent : IViewEvent>(val data: ViewEventWrapper<SubViewEvent>) :
        ViewData<ViewState, SubViewEvent>()
}
 */
