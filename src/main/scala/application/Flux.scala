package application

/**
  * Created by ryota on 2017/02/12.
  */

class Flux[Msg, State](private val reducer: (Msg, State) => State, initialState: State) {
  private var state: State = initialState
  private var subscribers: List[(State) => Unit] = List()

  def dispatch(msg: Msg) = {
    state = reducer(msg, state)
    subscribers.foreach { fn =>
      fn(state)
    }
  }

  def getState = state

  def subscribe(fn: (State) => Unit): Unit = {
    fn(state)
    subscribers = subscribers ++ List(fn)
  }
}

trait LinkFlux[Msg, State] {
  val flux: Flux[Msg, State]
}