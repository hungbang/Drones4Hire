<#include "Header.ftl">
<table class="contents" style="border-collapse: collapse;border-spacing: 0;table-layout: fixed;width: 100%;">
    <tbody>
    <tr>
        <td>Hello ${project.client.firstName} ${project.client.lastName}! </td>
    </tr>
    <tr>
        <td>
            <p>
                Your job "${project.project.title}" was expired case job start date was overdue. You need&nbsp;
                <a style="color:blue;font-weight:normal" href="${url}" target="_blank">update</a>&nbsp;job start date for showing your job again.
        </td>
    </tr>
    </tbody>
</table>
<#include "Footer.ftl">