//display of hide

  function displayOrHide(idName) {

    document.getElementById(idName).style.display = ((document.getElementById(idName).style.display=='none') ? "" : 'none');

  }


  function clpExtend(buttonId, tbId) {// collapse and extend

      if (document.getElementById(buttonId).innerHTML == "Hide") {
          document.getElementById(buttonId).innerHTML = "Show...";
          document.getElementById(tbId).style.display = 'none';

      } else {

          document.getElementById(buttonId).innerHTML = "Hide";
          document.getElementById(tbId).style.display = "";
      }

  }

  function openDetail(row,table)
  {
    for( var rowIndex = row.rowIndex, row = table.rows[rowIndex+1]; row = table.rows[rowIndex+1]; rowIndex += 1)
      {
        if( row.getAttribute("detail") == "yes" )
        {
            if( row.style.display != "none" )
            {
              row.style.display = "none";
            }
            else
            {
                row.style.display = "table-row";
            }
        }
        else
        {
          return;
        }
      }
  }

  var findNum = 0;
  function findIt(sid) {
      var span_obj = document.getElementsByName("search_span")
          if(span_obj.length != 0){
              for(var i=0;i<span_obj.length;i++){
                  span_obj[i].style.backgroundColor="";
              }

      }
      var str = document.getElementById(sid).value;
      if (str != "")
      {
          fHl(document.body, str);
      }
      else
      {
          alert("Empty search key word !");
          return;
      }
      /*
      if (findNum==0)
      {
          alert("'"+str+"'"+" not find !");
      }
      findNum = 0;
      */
  }

      function fHl(o, flag, rndColor, url){
          var bgCor=fgCor='';
          if(rndColor){
              bgCor=fRndCor(10, 20);
              fgCor=fRndCor(230, 255);
          } else {
              bgCor='#F0F';
              fgCor='black';
          }
          var re=new RegExp(flag, 'i');
          for(var i=0; i<o.childNodes.length; i++){
              var o_=o.childNodes[i];
              var o_p=o_.parentNode;
              if(o_.nodeType==1) {
                  fHl(o_, flag, rndColor, url);
               } else if (o_.nodeType==3) {
                  if(!(o_p.nodeName=='A')){
                      if(o_.data.search(re)==-1)continue;
                      var temp=fEleA(o_.data, flag);
                      o_p.replaceChild(temp, o_);
                      findNum += 1;
                  }
              }
          }
          //------------------------------------------------
          function fEleA(text, flag){
              var style=' style="background-color:'+bgCor+';color:'+fgCor+';" '
              var o=document.createElement('span');
              var str='';
              var re=new RegExp('('+flag+')', 'gi');
              if(url){
                  str=text.replace(re, '<a href="'+url+
                  '$1"'+style+'>$1</a>');
              } else {
                  str=text.replace(re, '<span name="search_span" '+style+'>$1</span>');
              }
              o.innerHTML=str;
              return o;
          }
          //------------------------------------------------
          function fRndCor(under, over){
              if(arguments.length==1){
                  var over=under;
                      under=0;
              }else if(arguments.length==0){
                  var under=0;
                  var over=255;
              }
              var r=fRandomBy(under, over).toString(16);
                  r=padNum(r, r, 2);
              var g=fRandomBy(under, over).toString(16);
                  g=padNum(g, g, 2);
              var b=fRandomBy(under, over).toString(16);
                  b=padNum(b, b, 2);
                  //defaultStatus=r+' '+g+' '+b
              return '#'+r+g+b;
              function fRandomBy(under, over){
                  switch(arguments.length){
                      case 1: return parseInt(Math.random()*under+1);
                      case 2: return parseInt(Math.random()*(over-under+1) + under);
                      default: return 0;
                  }
              }
              function padNum(str, num, len){
                  var temp='';
                  for(var i=0; i<len;temp+=num, i++);
                  return temp=(temp+=str).substr(temp.length-len);
              }
          }
      }

  function openClose(e, id)
  {
    document.getElementById(id).style.display = ((document.getElementById(id).style.display) == 'none' ? '':'none') ;

    text = e.target.childNodes[0].textContent;
    if(text == '[Path]')
      e.target.childNodes[0].textContent = '[Hide]';
    else
      e.target.childNodes[0].textContent = '[Path]';
  }

  function showHide(e,id)
  {
    e.target.parentNode.parentNode.style.display=(e.target.parentNode.parentNode.style.display=='none')?'':'none';
    document.getElementById(id).style.display = (document.getElementById(id).style.display=='none')?'':'none';
  }

  function positionSuite(suite)
  {
    if(suite != '')
    {
      var row = document.getElementById(suite).parentNode;

      if(row.tagName == 'BODY')
        return;
      document.getElementById("sctd").style.display = '';
      var table = document.getElementById("suite");

      document.getElementById(suite).innerHTML = "-";
      openDetail(row,table);
     }
  }

  var currentShow = "";
  var currentGrp = "";
  var flag = 0;
  function showRow(show,tableId,divName,grp) {
  document.getElementById("sect").style.display = document.getElementById("sect").style.display == "" ? "none" : "";
  document.getElementById("searchstr").value = "";
  var divMsg = document.getElementById("divMsg");
  divMsg.style.display = "";
  divMsg.style.left = (window.innerWidth - divMsg.scrollWidth) / 2 + "px";
  divMsg.style.top  = (window.innerHeight - divMsg.scrollHeight) / 2 + "px";
  if (currentGrp == grp && currentShow == show && document.getElementById(divName).style.display != 'none')
  {
  document.getElementById(divName).style.display = 'none';
  divMsg.style.display = "none";
  flag -=1;
  if(flag == 0)
    document.getElementById("sect").style.display = "none";
  return ;
  }
  document.getElementById("sect").style.display = "";
  flag += 1;
  setTimeout(function()
  {
      var table = document.getElementById(tableId);

      if (currentGrp == "" || currentGrp != grp || currentShow == "" || currentShow != show)
      {
          document.getElementById(divName).style.display = "";
      }
      if (currentGrp == grp && currentShow == show)
      {
          document.getElementById(divName).style.display =((document.getElementById(divName).style.display=='none') ? "" : 'none');

      }

      var num = 0;
      var emptyRow;
      for (var i = 2, row; row = table.rows[i]; i++)
      {
          var passFail = "";
          var isDetail = 'yes';
          var group = "";

          if( document.all )
          {
              passFail = row.state;
              isDetail = row.detail;
              group = row.group;
          }
          else
          {
              passFail = row.getAttribute('state');
              isDetail = row.getAttribute('detail');
              group = row.getAttribute('group');
          }

          if(passFail == 'empty')
          {
              emptyRow = row;
          }
          if ( (group == grp || grp == 'all') && isDetail != 'yes' && (show == 'all' || passFail == show) && passFail != 'empty')
          {
              row.style.display = 'table-row';
              if(row.cells[0] != "+")
              {
                row.cells[0].innerHTML = "+";
              }
              num += 1;
          }
          else
          {
              row.style.display = 'none';
          }
        }
        if(num == 0)
        {
            emptyRow.style.display = 'table-row';
        }
        currentShow = show ;
        currentGrp = grp;
        divMsg.style.display = "none";
        window.location.assign(location.pathname + "#" + divName);
    },100)
  }
  function showDetail(e,suite,tb)
  {
    var cell = e.target;
    var row = cell.parentNode;
    var table = document.getElementById(tb);
    var flag = document.getElementById(suite).innerHTML;

    if(flag == "+")
    {
      document.getElementById(suite).innerHTML = "-";
    }
    else
    {
      document.getElementById(suite).innerHTML = "+";
    }

    openDetail(row,table)
  }
  function openSuiteDetail()
  {
      var suite = window.location.hash.substr(1);
      positionSuite(suite);
      if(suite != "")
      {
          location.href = "#" + suite;
      }
  }
