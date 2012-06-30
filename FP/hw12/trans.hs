{-# LANGUAGE MultiParamTypeClasses, FunctionalDependencies, FlexibleInstances #-}

import Prelude hiding (catch)

import Control.Monad.Trans

newtype MaybeT m a = MaybeT { runMaybeT :: m (Maybe a) }

class Monad m => MonadMaybe m where
    abort :: m a
    recover :: m a -> m a -> m a

instance Monad m => Monad (MaybeT m) where
    return = undefined
    (>>=) = undefined
    fail = undefined

instance Monad m => MonadMaybe (MaybeT m) where
    abort = undefined
    recover = undefined

instance MonadTrans MaybeT where
    lift = undefined

instance MonadIO m => MonadIO (MaybeT m) where
    liftIO = undefined

---------------------------------------------------------------

newtype EitherT e m a = EitherT { runEitherT :: m (Either e a) }

class Monad m => MonadEither e m | m -> e where
    throw :: e -> m a
    catch :: m a -> (e -> m a) -> m a

instance Monad m => Monad (EitherT e m) where
    return = undefined
    (>>=) = undefined

instance Monad m => MonadEither e (EitherT e m) where
    throw = undefined
    catch = undefined

instance MonadTrans (EitherT e) where
    lift = undefined

instance MonadIO m => MonadIO (EitherT e m) where
    liftIO = undefined
