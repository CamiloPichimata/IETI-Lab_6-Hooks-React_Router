import React, {useState} from "react";
import useSWR from 'swr';
import axios from 'axios';
import $ from "jquery";
import '../styles/tasks.css';

export default function Tasks() {

    //const [tasks, setTasks] = useState(null);

    const url = 'http://localhost:8080/api/v2/tasks/';

    function mapTasks(data) {
        var html = "";
        $.each(data, function(index, value){
            html += '<tr>';
            html += '<td>'+value.id+'</td>';
            html += '<td>'+value.name+'</td>';
            html += '<td>'+value.description+'</td>';
            html += '<td>'+value.status+'</td>';
            html += '<td>'+value.assignedTo+'</td>';
            html += '<td>'+value.dueDate+'</td>';
            html += '<td>'+value.createAt+'</td>';
            html += '</tr>';
        });
        $('.table-list-tasks').html(html);
    }

    // const fetcherTasks = () => {
    //     $.ajax({
    //         url: url,
    //         type: 'GET',
    //         success: function (data) {
    //             console.log("-------data-------" + data);
    //             mapTasks(data);
    //         },
    //         error: function(error) {
    //             console.log('Error: ' + error);
    //         }
    //     });
    // }

    const fetcherTasks = () => axios.get(url).then(res => res.data);

    function getTasks() {
        const { data, error } = useSWR(url, fetcherTasks)

        if (error) {
            console.log("error: " + error);
            return <div>failed to load</div>
        }
        if (!data) return <div>loading...</div>
        mapTasks(data)
        return <div>Tasklist updated!</div>
    }

    return (
        <div>
            <h2>Tasks Page</h2>
            <div id="tasksTable">
                <table>
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Asigned To</th>
                            <th>Due Date</th>
                            <th>Created At</th>
                        </tr>
                    </thead>
                    <tbody className="table-list-tasks">

                    </tbody>
                </table>
                {getTasks()}
            </div>
        </div>
    );

}