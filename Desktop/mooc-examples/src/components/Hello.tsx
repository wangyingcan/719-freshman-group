import * as React from 'react'

interface IProps{
    sender:string;
    receiver:string;
}

function Hello(props:IProps){
    return <h1>
        {props.sender}向{props.receiver}问好
    </h1>;
}

export {Hello};