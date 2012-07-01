{-# LANGUAGE MultiParamTypeClasses, FunctionalDependencies, FlexibleInstances #-}

import Prelude hiding (catch)

import Control.Monad.Trans
import Control.Monad

newtype MaybeT m a = MaybeT { runMaybeT :: m (Maybe a) }

class Monad m => MonadMaybe m where
    abort :: m a
    recover :: m a -> m a -> m a

instance Monad m => Monad (MaybeT m) where
    return = MaybeT . return . Just
    
    x >>= f = MaybeT $ runMaybeT x >>= helper where
        helper Nothing = return Nothing
        helper (Just v) = runMaybeT $ f v
    
    fail _ = MaybeT $ return Nothing

instance Monad m => MonadMaybe (MaybeT m) where
    abort = undefined
    recover = undefined


instance MonadTrans MaybeT where
    lift = MaybeT . liftM Just

instance MonadIO m => MonadIO (MaybeT m) where
    liftIO = lift . liftIO

---------------------------------------------------------------

newtype EitherT e m a = EitherT { runEitherT :: m (Either e a) }

class Monad m => MonadEither e m | m -> e where
    throw :: e -> m a
    catch :: m a -> (e -> m a) -> m a

instance Monad m => Monad (EitherT e m) where
    return = EitherT . return. Right

    x >>= f = EitherT $ runEitherT x >>= helper where
        helper (Left l) = return (Left l)
        helper (Right r) = runEitherT $ f r

instance Monad m => MonadEither e (EitherT e m) where
    throw = undefined
    catch = undefined

instance MonadTrans (EitherT e) where
    lift = undefined --EitherT . liftM . Right

instance MonadIO m => MonadIO (EitherT e m) where
    liftIO = undefined
