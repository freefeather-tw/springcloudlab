import React from 'react';
import {Link} from 'react-router-dom';

const App2 = () => {

    return (
        <>
            <div>
                App 2
            </div>
            <div>
                <Link to="/home">
                    back to home
                </Link>
            </div>
        </>
        
    )
}

export default App2;