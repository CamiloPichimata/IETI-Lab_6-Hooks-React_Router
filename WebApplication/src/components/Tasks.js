import React, {useState} from "react";
import useSWR from 'swr';
import axios from 'axios';
import $ from "jquery";
import '../styles/tasks.css';

export default function Tasks() {

    //const [tableStyleRow, setTableStyleRow] = useState(null);

    const url = 'http://localhost:8080/api/v2/tasks/';

    function mapTasks(data) {

        //setTableStyleRow((tableStyleRow==='even') ? 'odd' : 'even');
 
        var html = "";
        $.each(data, function(index, value){
            html += '<tr class="'+((index%2===0) ? 'even' : 'odd')+'">';
            html +=     '<td>'+value.id+'</td>';
            html +=     '<td>'+value.name+'</td>';
            html +=     '<td>'+value.description+'</td>';
            html +=     '<td>'+value.status+'</td>';
            html +=     '<td>'+value.assignedTo+'</td>';
            html +=     '<td>'+value.dueDate+'</td>';
            html +=     '<td>'+value.createAt+'</td>';
            html += '</tr>';
        });
        $('.table-list-tasks').html(html);
    }

    const fetcherTasks = () => axios.get(url).then(res => res.data);

    function getTasks() {
        const { data, error } = useSWR(url, fetcherTasks)

        if (error) {
            console.log("error: " + error);
            return <div className="tasklist-state">failed to load</div>
        }
        if (!data) return <div className="tasklist-state">loading...</div>
        mapTasks(data)
        return <div className="tasklist-state">Tasklist updated!</div>
    }

    return (
        <div className="tasks-container">
            <h2 className='h2-title'>TaskApp</h2>
            <div className="all-centered">
                <div id="table-tasks">
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
        </div>
    );

}