<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Description of StationDatabase
 *
 * @author twk
 */
function preprint($s, $return=false) {
        $x = "<pre>";
        $x .= print_r($s, 1);
        $x .= "</pre>";
        if ($return) return $x;
        else print $x;
    } 

class Database {

    private $xmlFiles;
    private $dom;

    function __construct($xmlFiles) {
        $this->xmlFiles = $xmlFiles;
    }

    function load() {
        $this->dom = new DOMDocument('1.0','utf-8');
        $root = $this->dom->createElement("database");
        $this->dom->appendChild($this->dom->importNode($root,true));
        foreach ($this->xmlFiles as $file) {
            $dom=new DOMDocument();
            if (!$dom->load($file)) {
                return false;
            }
            //echo $dom;
            $xpdom = new DOMXPath($dom);
            $list = $xpdom->query("/*");
            foreach($list as $item){
                $root->appendChild($this->dom->importNode($item,true));
            };
        }
        //print_r($this->dom->saveXML());
    }

    function search($input) {
    	  //echo $input;
    	  //echo "\n";
        $s="ABCDEFGHIGKLMNOPQRSTUVWXYZÀÉÈÂÎÔàéèâîô";
        $a="abcdefghijklmnopqrstuvwxyzaeeaioaeeaio";
        //translate(.,'".$s."','".$a."')
        //translate('".$input . "','".$s."','".$a."')
        $xml = "<?xml version='1.0' encoding='UTF-8'?>\n";
        $xml .= "<results>\n";
        //echo "Searching for " . $input . "\n";
        $dxpath = new DOMXPath($this->dom);
        $results = $dxpath->query(
        "/*/*/*[@name]/@name[
        starts-with(translate(.,'".$s."','".$a."'), translate('".$input . "','".$s."','".$a."')) or
        contains(translate(.,'".$s."','".$a."'), translate('".$input . "','".$s."','".$a."')) or
        (translate('".$input . "','".$s."','".$a."') = 
         substring(translate(.,'".$s."','".$a."'),string-length(.)-string-length('" . $input . "')+1))]");
        $rarray = array();
        $prevresult = "";
        foreach ($results as $value) {
            $name = $value->value; 
            $tram=false;
            $bus=false;
            $dxpath2 = new DOMXPath($this->dom);
            $results2 = $dxpath2->query("/*/*/*[@name='".$value->value."']/*/@id");
                foreach ($results2 as $line) {
                    if(preg_match("/^[0-9]*$/", $line->value)>0){
                        $bus=true;
                    }
                    if(preg_match("/^[A-Z]$/", $line->value)>0){
                        $tram=true;
                    }
            }
            if($bus && !$tram){
                $xml.="<result type='bus' name='".$value->value."'/>\n";
            }
            else if(!$bus && $tram){
                $xml.="<result type='tram' name='".$value->value."'/>\n";
            } else if ($bus && $tram){
                $res = "<result type='bustram' name='".$value->value."'/>\n";
                if($prevresult!=$res){
                    $xml.=$res;
                }
                $prevresult = $res;
            } else{
                $xml.="<result type='address' name='".$value->value."'/>\n";
            }
        }
        $xml.="</results>";
        //preprint($xml);
        $xslDoc = new DOMDocument();
        $xslDoc->load("stations.xsl");
        $xmlDoc = new DOMDocument();
        $xmlDoc->loadXML($xml);
        $proc = new XSLTProcessor();
        $proc->importStylesheet($xslDoc);
        $tidy_config = array(
                     'clean' => true,
                     'output-xhtml' => true,
                     'wrap' => 0,
                     'indent'         => true,
                     'break-before-br' => true
            
                     );
        $html = $proc->transformToXML($xmlDoc);
        return $html;
        $tidy = tidy_parse_string($html, $tidy_config, 'UTF8'); 
        $tidy->cleanRepair();
        return $tidy;
  }
}

//[matches(.,'(^| )".$input."( |$)')]

?>
