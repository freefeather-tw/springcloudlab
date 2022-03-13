import React, {useState} from "react";
import {Link, Redirect} from 'react-router-dom';

const Home = () => {

    const [name, setName] = useState<string>("Jack");

    return (
        <>
            <div>
                This is home
            </div>
            <div>
                <input type='text' onChange={(e) => setName(e.target.value)} />
            </div>
            <div>
                Name is: {name}
            </div>
            <div>
                <Link to="/app2">
                    Link to app 2
                </Link>
            </div>
        </>
        

    )
}

export default Home;