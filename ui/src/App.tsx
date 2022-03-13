import React, {useState, lazy, Suspense} from 'react';
import {Route, Switch,BrowserRouter} from 'react-router-dom';

const App = () => {

    const Home = lazy(() => import('./Home'));

    const App2 = lazy(() => import('./App2'));

    return (
        <BrowserRouter>
            <Suspense fallback={<div>loading ...</div>}>
                <Switch>
                    <Route path="/home" component={Home} exact />
                    <Route path="/app2" component={App2} exact />
                </Switch>
            </Suspense>
        </BrowserRouter>
    );
}

export default App;
