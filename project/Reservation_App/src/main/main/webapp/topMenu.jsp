<div id="top-menu">
    <ul>
        <li><form id="top" action="index.jsp" method="get"><input class="top" type="submit" value="Home"></form></li>
        <li><form id="top" action="ChooseRole.jsp" method="get"><input class="top" type="submit" value="Login / Registration"></form></li>
    </ul>
</div>
<style>
    body {
        margin: 0;
        padding: 0;
    }
    #top-menu {
        width: 100%;
        background-color: #333;
        overflow: hidden;
        position: fixed; /* Fixes the menu at the top of the page */
        top: 0;
        left: 0;
        z-index: 1000; /* Ensures the menu is on top of other elements */
    }
    #top-menu ul {
        list-style-type: none;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center; /* Centers the menu items horizontally */
    }
    #top-menu li {
        float: left;
    }
    #top-menu li input {
        display: block;
        color: white;
        text-align: center;
        padding: 5px 8px;
        text-decoration: none;
        background-color:#333;
        border: none;
        cursor: pointer;
        font-family: inherit;
        font-size: inherit;
    }
    #top-menu li input:hover {
        background-color: #111;
    }
    .content {
        padding-top: 40px; /* Adds space to account for the fixed menu */
    }
    #top {
	    background-color:#333;
	    display: block;
        color: white;
        text-align: center;
        padding: 5px 8px;
        text-decoration: none;
        border: none;
        cursor: pointer;
        font-family: inherit;
        font-size: 12px;
    
    }
</style>