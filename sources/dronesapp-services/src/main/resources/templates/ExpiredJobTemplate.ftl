<#include "Header.ftl">
<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
    <tbody>
        <tr>
            <td>Hey ${user.username}! </td>
        </tr>
        <tr>
            <td>
                <p>
                    The date of service you specified for project, ${project.project.title}, has expired. Your project
                    has been removed from the <b>Job Board</b>, but is still active on your <b>My Projects page</b>. <br/>
                    At any time, you may go to <a style="color:blue;font-weight:normal" href="${url}" target="_blank">My Projects</a> and revise your project date.
            </td>
        </tr>
    </tbody>
</table>
<#include "Footer.ftl">