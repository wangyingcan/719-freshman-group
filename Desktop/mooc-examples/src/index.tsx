import * as React from 'react'
import * as ReactDOM from 'react-dom'

import {Hello} from './components/Hello'

const App=()=>{
    return <Hello sender="周文豪" receiver="王颖璨"></Hello>
}

ReactDOM.render(<App />,document.getElementById("root"));