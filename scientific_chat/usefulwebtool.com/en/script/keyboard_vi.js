$(document).ready(function() {
	keyboardLanguage = "vi";
	interceptKey = false;
	textareaFontSize = 14;
	textareaLineHeight = 18;
	characterCase = 0;
	// mode 1
	layoutMode1Lower = "<div class='vietnamese_accents'></div><table class='keyboard_viet' cellspacing='0'><tr><td><input type='button' class='letter' value='a' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#259;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#226;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#273;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='e' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#234;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='i' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='o' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#244;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#417;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='u' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#432;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='y' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#225;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7855;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7845;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#8363;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#233;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7871;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#237;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#243;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7889;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7899;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#250;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7913;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#253;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#224;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7857;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7847;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#232;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7873;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#236;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#242;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7891;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7901;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#249;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7915;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7923;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#7843;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7859;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7849;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#7867;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7875;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7881;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7887;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7893;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7903;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7911;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7917;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7927;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#227;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7861;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7851;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#7869;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7877;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#297;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#245;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7895;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7905;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#361;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7919;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7929;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#7841;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7863;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7853;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#7865;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7879;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7883;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7885;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7897;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7907;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7909;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7921;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7925;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr></table><div class='keyboard_holder'><div class='keyboard_viet_left'><div class='keyboard_viet_row_1'><input id='char_49' type='button' class='letter' value='1' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_50' type='button' class='letter' value='2' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_51' type='button' class='letter' value='3' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_52' type='button' class='letter' value='4' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_53' type='button' class='letter' value='5' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_54' type='button' class='letter' value='6' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_55' type='button' class='letter' value='7' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_56' type='button' class='letter' value='8' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_57' type='button' class='letter' value='9' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_48' type='button' class='letter' value='0' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_173' type='button' class='letter' value='-' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_61' type='button' class='letter' value='=' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_row_2'><input id='char_81' type='button' class='letter' value='q' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_87' type='button' class='letter' value='w' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_69' type='button' class='letter' value='e' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_82' type='button' class='letter' value='r' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_84' type='button' class='letter' value='t' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_89' type='button' class='letter' value='y' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_85' type='button' class='letter' value='u' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_73' type='button' class='letter' value='i' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_79' type='button' class='letter' value='o' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_80' type='button' class='letter' value='p' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_160' type='button' class='letter' value='[' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_221' type='button' class='letter' value=']' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_row_3'><input id='char_65' type='button' class='letter' value='a' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_83' type='button' class='letter' value='s' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_68' type='button' class='letter' value='d' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_70' type='button' class='letter' value='f' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_71' type='button' class='letter' value='g' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_72' type='button' class='letter' value='h' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_74' type='button' class='letter' value='j' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_75' type='button' class='letter' value='k' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_76' type='button' class='letter' value='l' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_59' type='button' class='letter' value=';' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_222' type='button' class='letter' value='&#39;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_row_4'><input id='char_90' type='button' class='letter' value='z' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_88' type='button' class='letter' value='x' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_67' type='button' class='letter' value='c' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_86' type='button' class='letter' value='v' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_66' type='button' class='letter' value='b' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_78' type='button' class='letter' value='n' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_77' type='button' class='letter' value='m' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_188' type='button' class='letter' value=',' onmousedown='manageChar(this)'  onmouseup='refocus()' /><input id='char_190' type='button' class='letter' value='.' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_191' type='button' class='letter' value='/' onmousedown='manageChar(this)' onmouseup='refocus()' /></div></div><div class='keyboard_viet_right'><input type='button' class='letter_text_viet' value='Backspace' onmousedown='manageBackspace()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_viet' value='Enter' onmousedown='manageEnter()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_viet' id='btn_caps' value='Caps Lock' onmousedown='manageCaps()'  onmouseup='refocus()' /><br /><input type='button' class='letter_text_viet' value='Space Bar' onmousedown='manageSpace()' onmouseup='refocus()' /><br /></div></div>";
	layoutMode1Upper = "<div class='vietnamese_accents'></div><table class='keyboard_viet' cellspacing='0'><tr><td><input type='button' class='letter' value='A' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#258;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#194;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#272;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='E' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#202;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='I' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='O' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#212;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#416;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='U' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#431;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='Y' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#193;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7854;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7844;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#8363;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#201;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7870;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#205;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#211;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7888;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7898;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#218;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7912;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#221;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#192;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7856;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7846;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#200;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7872;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#204;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#210;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7890;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7900;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#217;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7914;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7922;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#7842;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7858;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7848;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#7866;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7874;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7880;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7886;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7892;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7902;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7910;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7916;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7926;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#195;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7860;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7850;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#7868;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7876;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#296;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#213;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7894;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7904;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#360;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7918;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7928;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr><tr><td><input type='button' class='letter' value='&#7840;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7862;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7852;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td></td><td class='padding'></td><td><input type='button' class='letter' value='&#7864;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7878;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7882;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7884;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7896;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7906;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7908;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td><input type='button' class='letter' value='&#7920;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td><td class='padding'></td><td><input type='button' class='letter' value='&#7924;' onmousedown='manageChar(this)' onmouseup='refocus()' /></td></tr></table><div class='keyboard_holder'><div class='keyboard_viet_left'><div class='keyboard_viet_row_1'><input id='char_49' type='button' class='letter' value='!' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_50' type='button' class='letter' value='@' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_51' type='button' class='letter' value='#' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_52' type='button' class='letter' value='$' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_53' type='button' class='letter' value='%' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_54' type='button' class='letter' value='^' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_55' type='button' class='letter' value='&' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_56' type='button' class='letter' value='*' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_57' type='button' class='letter' value='(' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_48' type='button' class='letter' value=')' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_173' type='button' class='letter' value='_' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_61' type='button' class='letter' value='+' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_row_2'><input id='char_81' type='button' class='letter' value='Q' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_87' type='button' class='letter' value='W' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_69' type='button' class='letter' value='E' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_82' type='button' class='letter' value='R' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_84' type='button' class='letter' value='T' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_89' type='button' class='letter' value='Y' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_85' type='button' class='letter' value='U' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_73' type='button' class='letter' value='I' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_79' type='button' class='letter' value='O' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_80' type='button' class='letter' value='P' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_160' type='button' class='letter' value='{' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_221' type='button' class='letter' value='}' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_row_3'><input id='char_65' type='button' class='letter' value='A' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_83' type='button' class='letter' value='S' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_68' type='button' class='letter' value='D' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_70' type='button' class='letter' value='F' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_71' type='button' class='letter' value='G' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_72' type='button' class='letter' value='H' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_74' type='button' class='letter' value='J' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_75' type='button' class='letter' value='K' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_76' type='button' class='letter' value='L' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_59' type='button' class='letter' value=':' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_222' type='button' class='letter' value='&#34;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_row_4'><input id='char_90' type='button' class='letter' value='Z' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_88' type='button' class='letter' value='X' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_67' type='button' class='letter' value='C' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_86' type='button' class='letter' value='V' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_66' type='button' class='letter' value='B' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_78' type='button' class='letter' value='N' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_77' type='button' class='letter' value='M' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_188' type='button' class='letter' value='<' onmousedown='manageChar(this)'  onmouseup='refocus()' /><input id='char_190' type='button' class='letter' value='>' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_191' type='button' class='letter' value='?' onmousedown='manageChar(this)' onmouseup='refocus()' /></div></div><div class='keyboard_viet_right'><input type='button' class='letter_text_viet' value='Backspace' onmousedown='manageBackspace()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_viet' value='Enter' onmousedown='manageEnter()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_viet' id='btn_caps' value='Caps Lock' onmousedown='manageCaps()'  onmouseup='refocus()' /><br /><input type='button' class='letter_text_viet' value='Space Bar' onmousedown='manageSpace()' onmouseup='refocus()' /><br /></div></div>";
	// mode 2
	layoutMode2Lower = "<div class='keyboard_holder'><div class='keyboard_viet_mode2_left'><div class='keyboard_viet_mode2_row_special_top'><input type='button' class='letter_big_box' value='1' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='2' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='3' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='4' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='5' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='6' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='7' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='8' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='9' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='0' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='-' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='=' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_1'><input id='char_192' type='button' class='letter_big_box' value='`' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_49' type='button' class='letter_big_box' value='&#259;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_50' type='button' class='letter_big_box' value='&#226;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_51' type='button' class='letter_big_box' value='&#234;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_52' type='button' class='letter_big_box' value='&#244;' onmousedown='manageChar(this)' onmouseup='refocus()' /><a id='char_53' class='viet_btns mode2_btn_sac' href='javascript:manageVietAccent(1)' onmouseup='refocus()' ></a><a id='char_54' class='viet_btns mode2_btn_huyen' href='javascript:manageVietAccent(2)' onmouseup='refocus()' ></a><a id='char_55' class='viet_btns mode2_btn_hoi' href='javascript:manageVietAccent(3)' onmouseup='refocus()' ></a><a id='char_56' class='viet_btns mode2_btn_nga' href='javascript:manageVietAccent(4)' onmouseup='refocus()' ></a><a id='char_57' class='viet_btns mode2_btn_nang' href='javascript:manageVietAccent(5)' onmouseup='refocus()' ></a><input id='char_48' type='button' class='letter_big_box' value='&#273;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_173' type='button' class='letter_big_box' value='-' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_61' type='button' class='letter_big_box' value='&#8363;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_2'><input id='char_81' type='button' class='letter_big_box' value='q' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_87' type='button' class='letter_big_box' value='w' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_69' type='button' class='letter_big_box' value='e' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_82' type='button' class='letter_big_box' value='r' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_84' type='button' class='letter_big_box' value='t' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_89' type='button' class='letter_big_box' value='y' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_85' type='button' class='letter_big_box' value='u' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_73' type='button' class='letter_big_box' value='i' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_79' type='button' class='letter_big_box' value='o' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_80' type='button' class='letter_big_box' value='p' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_160' type='button' class='letter_big_box' value='&#432;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_221' type='button' class='letter_big_box' value='&#417;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_220' type='button' class='letter_big_box' value='&#92;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_3'><input id='char_65' type='button' class='letter_big_box' value='a' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_83' type='button' class='letter_big_box' value='s' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_68' type='button' class='letter_big_box' value='d' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_70' type='button' class='letter_big_box' value='f' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_71' type='button' class='letter_big_box' value='g' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_72' type='button' class='letter_big_box' value='h' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_74' type='button' class='letter_big_box' value='j' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_75' type='button' class='letter_big_box' value='k' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_76' type='button' class='letter_big_box' value='l' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_59' type='button' class='letter_big_box' value=';' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_222' type='button' class='letter_big_box' value='&#39;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_4'><input id='char_90' type='button' class='letter_big_box' value='z' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_88' type='button' class='letter_big_box' value='x' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_67' type='button' class='letter_big_box' value='c' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_86' type='button' class='letter_big_box' value='v' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_66' type='button' class='letter_big_box' value='b' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_78' type='button' class='letter_big_box' value='n' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_77' type='button' class='letter_big_box' value='m' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_188' type='button' class='letter_big_box' value=',' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_190' type='button' class='letter_big_box' value='.' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_191' type='button' class='letter_big_box' value='/' onmousedown='manageChar(this)' onmouseup='refocus()' /></div></div><div class='keyboard_viet_mode2_right'><input type='button' class='letter_text_big_right' value='Backspace' onmousedown='manageBackspace()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Enter' onmousedown='manageEnter()' onmouseup='refocus()' /><br /><input id='btn_caps' type='button' class='letter_text_big_right' value='Caps Lock' onmousedown='manageCaps()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Space Bar' onmousedown='manageSpace()' onmouseup='refocus()' /><br /></div></div>"
	layoutMode2Upper = "<div class='keyboard_holder'><div class='keyboard_viet_mode2_left'><div class='keyboard_viet_mode2_row_special_top'><input type='button' class='letter_big_box' value='!' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='@' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='#' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='$' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='%' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='^' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='&' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='*' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='(' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value=')' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='_' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='+' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_1'><input id='char_192' type='button' class='letter_big_box' value='~' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_49' type='button' class='letter_big_box' value='&#258;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_50' type='button' class='letter_big_box' value='&#194;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_51' type='button' class='letter_big_box' value='&#202;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_52' type='button' class='letter_big_box' value='&#212;' onmousedown='manageChar(this)' onmouseup='refocus()' /><a id='char_53' class='viet_btns mode2_btn_sac' href='javascript:manageVietAccent(1)' onmouseup='refocus()' ></a><a id='char_54' class='viet_btns mode2_btn_huyen' href='javascript:manageVietAccent(2)' onmouseup='refocus()' ></a><a id='char_55' class='viet_btns mode2_btn_hoi' href='javascript:manageVietAccent(3)' onmouseup='refocus()' ></a><a id='char_56' class='viet_btns mode2_btn_nga' href='javascript:manageVietAccent(4)' onmouseup='refocus()' ></a><a id='char_57' class='viet_btns mode2_btn_nang' href='javascript:manageVietAccent(5)' onmouseup='refocus()' ></a><input id='char_48' type='button' class='letter_big_box' value='&#272;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_173' type='button' class='letter_big_box' value='&cent;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_61' type='button' class='letter_big_box' value='&euro;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_2'><input id='char_81' type='button' class='letter_big_box' value='Q' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_87' type='button' class='letter_big_box' value='W' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_69' type='button' class='letter_big_box' value='E' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_82' type='button' class='letter_big_box' value='R' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_84' type='button' class='letter_big_box' value='T' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_89' type='button' class='letter_big_box' value='Y' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_85' type='button' class='letter_big_box' value='U' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_73' type='button' class='letter_big_box' value='I' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_79' type='button' class='letter_big_box' value='O' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_80' type='button' class='letter_big_box' value='P' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_160' type='button' class='letter_big_box' value='&#431;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_221' type='button' class='letter_big_box' value='&#416;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_220' type='button' class='letter_big_box' value='|' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_3'><input id='char_65' type='button' class='letter_big_box' value='A' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_83' type='button' class='letter_big_box' value='S' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_68' type='button' class='letter_big_box' value='D' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_70' type='button' class='letter_big_box' value='F' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_71' type='button' class='letter_big_box' value='G' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_72' type='button' class='letter_big_box' value='H' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_74' type='button' class='letter_big_box' value='J' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_75' type='button' class='letter_big_box' value='K' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_76' type='button' class='letter_big_box' value='L' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_59' type='button' class='letter_big_box' value=':' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_222' type='button' class='letter_big_box' value='&quot;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_4'><input id='char_90' type='button' class='letter_big_box' value='Z' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_88' type='button' class='letter_big_box' value='X' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_67' type='button' class='letter_big_box' value='C' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_86' type='button' class='letter_big_box' value='V' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_66' type='button' class='letter_big_box' value='B' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_78' type='button' class='letter_big_box' value='N' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_77' type='button' class='letter_big_box' value='M' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_188' type='button' class='letter_big_box' value='<' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_190' type='button' class='letter_big_box' value='>' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_191' type='button' class='letter_big_box' value='?' onmousedown='manageChar(this)' onmouseup='refocus()' /></div></div><div class='keyboard_viet_mode2_right'><input type='button' class='letter_text_big_right' value='Backspace' onmousedown='manageBackspace()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Enter' onmousedown='manageEnter()' onmouseup='refocus()' /><br /><input id='btn_caps' type='button' class='letter_text_big_right' value='Caps Lock' onmousedown='manageCaps()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Space Bar' onmousedown='manageSpace()' onmouseup='refocus()' /><br /></div></div>";
	// mode 3
	layoutMode3Lower = "<div class='keyboard_holder'><div class='keyboard_viet_mode2_left'><div class='keyboard_viet_mode2_row_special_top'><input type='button' class='letter_big_box' value='1' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='2' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='3' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='4' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='5' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='6' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='7' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='8' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='9' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='0' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='-' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='=' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_1'><input id='char_192' type='button' class='letter_big_box' value='&euro;' onmousedown='manageChar(this)' onmouseup='refocus()' /><a id='char_49' class='viet_btns mode3_btn_sac' href='javascript:manageVietAccent(1)' onmouseup='refocus()' ></a><a id='char_50' class='viet_btns mode3_btn_huyen' href='javascript:manageVietAccent(2)' onmouseup='refocus()' ></a><a id='char_51' class='viet_btns mode3_btn_hoi' href='javascript:manageVietAccent(3)' onmouseup='refocus()' ></a><a id='char_52' class='viet_btns mode3_btn_nga' href='javascript:manageVietAccent(4)' onmouseup='refocus()' ></a><a id='char_53' class='viet_btns mode3_btn_nang' href='javascript:manageVietAccent(5)' onmouseup='refocus()' ></a><a id='char_54' class='viet_btns mode3_btn_mu' href='javascript:manageVietAccent(6)' onmouseup='refocus()' ></a><a id='char_55' class='viet_btns mode3_btn_ngoac' href='javascript:manageVietAccent(7)' onmouseup='refocus()' ></a><a id='char_56' class='viet_btns mode3_btn_a' href='javascript:manageVietAccent(8)' onmouseup='refocus()' ></a><input id='char_57' type='button' class='letter_big_box' value='&#273;' onmousedown='manageVietAccent(9)' onmouseup='refocus()' /><input id='char_48' type='button' class='letter_big_box' value='&#8363;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_173' type='button' class='letter_big_box' value='-' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_61' type='button' class='letter_big_box' value='=' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_2'><input id='char_81' type='button' class='letter_big_box' value='q' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_87' type='button' class='letter_big_box' value='w' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_69' type='button' class='letter_big_box' value='e' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_82' type='button' class='letter_big_box' value='r' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_84' type='button' class='letter_big_box' value='t' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_89' type='button' class='letter_big_box' value='y' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_85' type='button' class='letter_big_box' value='u' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_73' type='button' class='letter_big_box' value='i' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_79' type='button' class='letter_big_box' value='o' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_80' type='button' class='letter_big_box' value='p' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_160' type='button' class='letter_big_box' value='&#432;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_221' type='button' class='letter_big_box' value='&#417;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_220' type='button' class='letter_big_box' value='&#92;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_3'><input id='char_65' type='button' class='letter_big_box' value='a' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_83' type='button' class='letter_big_box' value='s' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_68' type='button' class='letter_big_box' value='d' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_70' type='button' class='letter_big_box' value='f' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_71' type='button' class='letter_big_box' value='g' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_72' type='button' class='letter_big_box' value='h' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_74' type='button' class='letter_big_box' value='j' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_75' type='button' class='letter_big_box' value='k' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_76' type='button' class='letter_big_box' value='l' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_59' type='button' class='letter_big_box' value=';' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_222' type='button' class='letter_big_box' value='&#39;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_4'><input id='char_90' type='button' class='letter_big_box' value='z' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_88' type='button' class='letter_big_box' value='x' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_67' type='button' class='letter_big_box' value='c' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_86' type='button' class='letter_big_box' value='v' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_66' type='button' class='letter_big_box' value='b' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_78' type='button' class='letter_big_box' value='n' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_77' type='button' class='letter_big_box' value='m' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_188' type='button' class='letter_big_box' value=',' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_190' type='button' class='letter_big_box' value='.' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_191' type='button' class='letter_big_box' value='/' onmousedown='manageChar(this)' onmouseup='refocus()' /></div></div><div class='keyboard_viet_mode2_right'><input type='button' class='letter_text_big_right' value='Backspace' onmousedown='manageBackspace()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Enter' onmousedown='manageEnter()' onmouseup='refocus()' /><br /><input id='btn_caps' type='button' class='letter_text_big_right' value='Caps Lock' onmousedown='manageCaps()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Space Bar' onmousedown='manageSpace()' onmouseup='refocus()' /><br /></div></div>"
	layoutMode3Upper = "<div class='keyboard_holder'><div class='keyboard_viet_mode2_left'><div class='keyboard_viet_mode2_row_special_top'><input type='button' class='letter_big_box' value='!' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='@' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='#' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='$' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='%' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='^' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='&' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='*' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='(' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value=')' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='_' onmousedown='manageChar(this)' onmouseup='refocus()' /><input type='button' class='letter_big_box' value='+' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_1'><input id='char_192' type='button' class='letter_big_box' value='&pound;' onmousedown='manageChar(this)' onmouseup='refocus()' /><a id='char_49' class='viet_btns mode3_btn_sac' href='javascript:manageVietAccent(1)' onmouseup='refocus()' ></a><a id='char_50' class='viet_btns mode3_btn_huyen' href='javascript:manageVietAccent(2)' onmouseup='refocus()' ></a><a id='char_51' class='viet_btns mode3_btn_hoi' href='javascript:manageVietAccent(3)' onmouseup='refocus()' ></a><a id='char_52' class='viet_btns mode3_btn_nga' href='javascript:manageVietAccent(4)' onmouseup='refocus()' ></a><a id='char_53' class='viet_btns mode3_btn_nang' href='javascript:manageVietAccent(5)' onmouseup='refocus()' ></a><a id='char_54' class='viet_btns mode3_btn_mu' href='javascript:manageVietAccent(6)' onmouseup='refocus()' ></a><a id='char_55' class='viet_btns mode3_btn_ngoac' href='javascript:manageVietAccent(7)' onmouseup='refocus()' ></a><a id='char_56' class='viet_btns mode3_btn_a' href='javascript:manageVietAccent(8)' onmouseup='refocus()' ></a><input id='char_57' type='button' class='letter_big_box' value='&#272;' onmousedown='manageVietAccent(9)' onmouseup='refocus()' /><input id='char_48' type='button' class='letter_big_box' value='&cent;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_173' type='button' class='letter_big_box' value='&ccedil;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_61' type='button' class='letter_big_box' value='&Ccedil;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_2'><input id='char_81' type='button' class='letter_big_box' value='Q' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_87' type='button' class='letter_big_box' value='W' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_69' type='button' class='letter_big_box' value='E' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_82' type='button' class='letter_big_box' value='R' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_84' type='button' class='letter_big_box' value='T' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_89' type='button' class='letter_big_box' value='Y' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_85' type='button' class='letter_big_box' value='U' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_73' type='button' class='letter_big_box' value='I' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_79' type='button' class='letter_big_box' value='O' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_80' type='button' class='letter_big_box' value='P' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_160' type='button' class='letter_big_box' value='&#431;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_221' type='button' class='letter_big_box' value='&#416;' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_220' type='button' class='letter_big_box' value='|' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_3'><input id='char_65' type='button' class='letter_big_box' value='A' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_83' type='button' class='letter_big_box' value='S' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_68' type='button' class='letter_big_box' value='D' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_70' type='button' class='letter_big_box' value='F' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_71' type='button' class='letter_big_box' value='G' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_72' type='button' class='letter_big_box' value='H' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_74' type='button' class='letter_big_box' value='J' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_75' type='button' class='letter_big_box' value='K' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_76' type='button' class='letter_big_box' value='L' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_59' type='button' class='letter_big_box' value=':' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_222' type='button' class='letter_big_box' value='&quot;' onmousedown='manageChar(this)' onmouseup='refocus()' /></div><div class='keyboard_viet_mode2_row_4'><input id='char_90' type='button' class='letter_big_box' value='Z' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_88' type='button' class='letter_big_box' value='X' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_67' type='button' class='letter_big_box' value='C' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_86' type='button' class='letter_big_box' value='V' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_66' type='button' class='letter_big_box' value='B' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_78' type='button' class='letter_big_box' value='N' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_77' type='button' class='letter_big_box' value='M' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_188' type='button' class='letter_big_box' value='<' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_190' type='button' class='letter_big_box' value='>' onmousedown='manageChar(this)' onmouseup='refocus()' /><input id='char_191' type='button' class='letter_big_box' value='?' onmousedown='manageChar(this)' onmouseup='refocus()' /></div></div><div class='keyboard_viet_mode2_right'><input type='button' class='letter_text_big_right' value='Backspace' onmousedown='manageBackspace()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Enter' onmousedown='manageEnter()' onmouseup='refocus()' /><br /><input id='btn_caps' type='button' class='letter_text_big_right' value='Caps Lock' onmousedown='manageCaps()' onmouseup='refocus()' /><br /><input type='button' class='letter_text_big_right' value='Space Bar' onmousedown='manageSpace()' onmouseup='refocus()' /><br /></div></div>"
	
	atLeastOneLayoutIsChosen = false;
	for (k=0; k<$("form input:radio").length; k++){
		if ($("form input:radio")[k].checked == true) {
			keyboardMode = k;
			atLeastOneLayoutIsChosen = true;
		}
	}
	if (atLeastOneLayoutIsChosen == false) {
		$("form input:radio")[0].checked = true;
		keyboardMode = 0;
	}
	manageMode(keyboardMode);
	
	matrix = new Array(); matrix["vowelFamily"] = new Array("a", "ă", "â", "e", "ê", "i", "o", "ô", "ơ", "u", "ư", "y", "A", "Ă", "Â", "E", "Ê", "I", "O", "Ô", "Ơ", "U", "Ư", "Y");matrix["vowels"] = new Array();matrix["vowels"]["a"] = new Array("a", "á", "à", "ả", "ã", "ạ");matrix["vowels"]["ă"] = new Array("ă", "ắ", "ằ", "ẳ", "ẵ", "ặ");matrix["vowels"]["â"] = new Array("â", "ấ", "ầ", "ẩ", "ẫ", "ậ");matrix["vowels"]["e"] = new Array("e", "é", "è", "ẻ", "ẽ", "ẹ");matrix["vowels"]["ê"] = new Array("ê", "ế", "ề", "ể", "ễ", "ệ");matrix["vowels"]["i"] = new Array("i", "í", "ì", "ỉ", "ĩ", "ị");matrix["vowels"]["o"] = new Array("o", "ó", "ò", "ỏ", "õ", "ọ");matrix["vowels"]["ô"] = new Array("ô", "ố", "ồ", "ổ", "ỗ", "ộ");matrix["vowels"]["ơ"] = new Array("ơ", "ớ", "ờ", "ở", "ỡ", "ợ");matrix["vowels"]["u"] = new Array("u", "ú", "ù", "ủ", "ũ", "ụ");matrix["vowels"]["ư"] = new Array("ư", "ứ", "ừ", "ử", "ữ", "ự");matrix["vowels"]["y"] = new Array("y", "ý", "ỳ", "ỷ", "ỹ", "ỵ");matrix["vowels"]["A"] = new Array("A", "Á", "À", "Ả", "Ã", "Ạ");matrix["vowels"]["Ă"] = new Array("Ă", "Ắ", "Ằ", "Ẳ", "Ẵ", "Ặ");matrix["vowels"]["Â"] = new Array("Â", "Ấ", "Ầ", "Ẩ", "Ẫ", "Ậ");matrix["vowels"]["E"] = new Array("E", "É", "È", "Ẻ", "Ẽ", "Ẹ");matrix["vowels"]["Ê"] = new Array("Ê", "Ế", "Ề", "Ể", "Ễ", "Ệ");matrix["vowels"]["I"] = new Array("I", "Í", "Ì", "Ỉ", "Ĩ", "Ị");matrix["vowels"]["O"] = new Array("O", "Ó", "Ò", "Ỏ", "Õ", "Ọ");matrix["vowels"]["Ô"] = new Array("Ô", "Ố", "Ồ", "Ổ", "Ỗ", "Ộ");matrix["vowels"]["Ơ"] = new Array("Ơ", "Ớ", "Ờ", "Ở", "Ỡ", "Ợ");matrix["vowels"]["U"] = new Array("U", "Ú", "Ù", "Ủ", "Ũ", "Ụ");matrix["vowels"]["Ư"] = new Array("Ư", "Ứ", "Ừ", "Ử", "Ữ", "Ự");matrix["vowels"]["Y"] = new Array("Y", "Ý", "Ỳ", "Ỷ", "Ỹ", "Ỵ"); matrix["doubleTap"] = new Array("a", "d", "e", "i", "o", "u", "y", "A", "D", "E", "I", "O", "U", "Y")
});

function manageKeyboard(operator) {
	switch(operator) {
		case 0 :
			switch(keyboardMode) {
				// All characters
				case 0 :
					document.getElementById("keyboard").innerHTML = layoutMode1Lower;
				break;
				// Like in vietnam
				case 1 :
					document.getElementById("keyboard").innerHTML = layoutMode2Lower;
				break;
				// Like VPSKeys
				case 2 :
					document.getElementById("keyboard").innerHTML = layoutMode3Lower;
				break;
			}			
		break;
		case 1 :
			switch(keyboardMode) {
				// All characters
				case 0 :
					document.getElementById("keyboard").innerHTML = layoutMode1Upper;
				break;
				// Like in vietnam
				case 1 :
					document.getElementById("keyboard").innerHTML = layoutMode2Upper;
				break;
				// Like VPSKeys
				case 2 :
					document.getElementById("keyboard").innerHTML = layoutMode3Upper;
				break;
			}	
		break;
	}
	characterCase = operator;
	manageCapsBtnLooks();
}
function manageMode(operator) {
	switch(operator) {
		case 0 :
			switch(characterCase) {
				case 0 :
					document.getElementById("keyboard").innerHTML = layoutMode1Lower;
				break;
				case 1 :
					document.getElementById("keyboard").innerHTML = layoutMode1Upper;
				break;
			}
			$("#convertDoublesToAccent").hide();
			interceptKey = false;
		break;
		case 1 :
			switch(characterCase) {
				case 0 :
					document.getElementById("keyboard").innerHTML = layoutMode2Lower;
				break;
				case 1 :
					document.getElementById("keyboard").innerHTML = layoutMode2Upper;
				break;
			}
			$("#convertDoublesToAccent").hide();
			interceptKey = true;
		break;
		case 2 :
			switch(characterCase) {
				case 0 :
					document.getElementById("keyboard").innerHTML = layoutMode3Lower;
				break;
				case 1 :
					document.getElementById("keyboard").innerHTML = layoutMode3Upper;
				break;
			}
			$("#convertDoublesToAccent").show();
			interceptKey = true;
		break;
	}
	keyboardMode = operator;
	manageCapsBtnLooks();
	refocus();
}
function manageVietAccent(who) {	
	canvas = document.getElementById("canvasId");
	isAddingSpecialD = false;
	if (typeof document.selection != 'undefined') {
		caret = getCaret();
		if (caret == -1) {
			caret = canvas.value.length;
		}
		begining = canvas.value.substr(0, caret);
		ending = canvas.value.substr(caret, canvas.value.length);
		////////////////
		switch(keyboardMode) {
			case 1 :
				transformCharBasicFiveIntonations(begining, ending, who)
			break;
			case 2 :
				transformCharAllAccentTypes(begining, ending, who)
			break;
		}		
		////////////////		
		cptr = 0;		
		for (k=0; k<caret; k++) {
			if (canvas.value[k] == "\n") {
				if (ie9 != true) {
					cptr++;
				} else {
					// do nothing
				}				
			}
		}
		if (isAddingSpecialD == false) {
			setCaretToPos(caret-(cptr));
		} else {
			setCaretToPos(caret-(cptr)+1);
		}		
	} else if (typeof canvas.selectionStart != 'undefined') {
		beginingPos = canvas.selectionStart;
		begining = canvas.value.substr(0, beginingPos)
		endingPos = canvas.selectionEnd;
		ending = canvas.value.substr(endingPos);
		////////////////
		switch(keyboardMode) {
			case 1 :
				transformCharBasicFiveIntonations(begining, ending, who)
			break;
			case 2 :
				transformCharAllAccentTypes(begining, ending, who)
			break;
		}	
		////////////////		
		if (isAddingSpecialD == false) {
			canvas.selectionStart = beginingPos;
			canvas.selectionEnd = beginingPos;
		} else {
			canvas.selectionStart = beginingPos+1;
			canvas.selectionEnd = beginingPos+1;
		}	
		canvas.scrollTop = canvas.scrollHeight;
		
	}
}
function manageDoubleTap(who) {
	if ($("#convertDoubleVietLetters").is(":checked") == true) {
		canvas = document.getElementById("canvasId");
		if (typeof document.selection != 'undefined') {
			caret = getCaret();
			if (caret == -1) {
				caret = canvas.value.length;
			}
			begining = canvas.value.substr(0, caret);
			ending = canvas.value.substr(caret, canvas.value.length);
			
			doubleTapReplaced = transformCharDoubleTap(begining, ending, who);
			if (doubleTapReplaced != false) {
				cptr = 0;		
				for (k=0; k<caret; k++) {
					if (canvas.value[k] == "\n") {
						if (ie9 != true) {
							cptr++;
						} else {
							// do nothing
						}				
					}
				}
				setCaretToPos(caret-(cptr));	
			}				
		} else if (typeof canvas.selectionStart != 'undefined') {
			beginingPos = canvas.selectionStart;
			begining = canvas.value.substr(0, beginingPos)
			endingPos = canvas.selectionEnd;
			ending = canvas.value.substr(endingPos);
			
			doubleTapReplaced = transformCharDoubleTap(begining, ending, who);			
			if (doubleTapReplaced != false) {
				canvas.selectionStart = beginingPos;
				canvas.selectionEnd = beginingPos;
				canvas.scrollTop = canvas.scrollHeight;
			}			
		}
		return doubleTapReplaced;
	} else {
		return false;
	}	
}
function transformCharBasicFiveIntonations(begining, ending, who) {
	lastChar = begining.substr(begining.length-1, begining.length)
	for (k=0; k<matrix["vowelFamily"].length; k++) {
		for (r=0; r<matrix["vowels"][matrix["vowelFamily"][k]].length; r++) {
			if (lastChar == matrix["vowels"][matrix["vowelFamily"][k]][r]) {
				incommingString = matrix["vowels"][matrix["vowelFamily"][k]][who]
				canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
			}
		}
	}
}
function transformCharAllAccentTypes(begining, ending, who) {
	switch(who) {
		case 1 :
			transformCharBasicFiveIntonations(begining, ending, who)
		break;
		case 2 :
			transformCharBasicFiveIntonations(begining, ending, who)
		break;
		case 3 :
			transformCharBasicFiveIntonations(begining, ending, who)
		break;
		case 4 :
			transformCharBasicFiveIntonations(begining, ending, who)
		break;
		case 5 :
			transformCharBasicFiveIntonations(begining, ending, who)
		break;
		case 6 :
			lastChar = begining.substr(begining.length-1, begining.length)
			for (k=0; k<matrix["vowels"]["a"].length; k++) {
				if (matrix["vowels"]["a"][k] == lastChar) {
					incommingString = matrix["vowels"]["â"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["ă"].length; k++) {
				if (matrix["vowels"]["ă"][k] == lastChar) {
					incommingString = matrix["vowels"]["â"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["e"].length; k++) {
				if (matrix["vowels"]["e"][k] == lastChar) {
					incommingString = matrix["vowels"]["ê"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["o"].length; k++) {
				if (matrix["vowels"]["o"][k] == lastChar) {
					incommingString = matrix["vowels"]["ô"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["ơ"].length; k++) {
				if (matrix["vowels"]["ơ"][k] == lastChar) {
					incommingString = matrix["vowels"]["ô"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["A"].length; k++) {
				if (matrix["vowels"]["A"][k] == lastChar) {
					incommingString = matrix["vowels"]["Â"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["Ă"].length; k++) {
				if (matrix["vowels"]["Ă"][k] == lastChar) {
					incommingString = matrix["vowels"]["Â"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["E"].length; k++) {
				if (matrix["vowels"]["E"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ê"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["O"].length; k++) {
				if (matrix["vowels"]["O"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ô"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["Ơ"].length; k++) {
				if (matrix["vowels"]["Ơ"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ô"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
		break;
		case 7 :
			lastChar = begining.substr(begining.length-1, begining.length)
			for (k=0; k<matrix["vowels"]["o"].length; k++) {
				if (matrix["vowels"]["o"][k] == lastChar) {
					incommingString = matrix["vowels"]["ơ"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["ô"].length; k++) {
				if (matrix["vowels"]["ô"][k] == lastChar) {
					incommingString = matrix["vowels"]["ơ"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["u"].length; k++) {
				if (matrix["vowels"]["u"][k] == lastChar) {
					incommingString = matrix["vowels"]["ư"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["O"].length; k++) {
				if (matrix["vowels"]["O"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ơ"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["Ô"].length; k++) {
				if (matrix["vowels"]["Ô"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ơ"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["U"].length; k++) {
				if (matrix["vowels"]["U"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ư"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
		break;
		case 8 :
			lastChar = begining.substr(begining.length-1, begining.length)
			for (k=0; k<matrix["vowels"]["a"].length; k++) {
				if (matrix["vowels"]["a"][k] == lastChar) {
					incommingString = matrix["vowels"]["ă"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["â"].length; k++) {
				if (matrix["vowels"]["â"][k] == lastChar) {
					incommingString = matrix["vowels"]["ă"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			lastChar = begining.substr(begining.length-1, begining.length)
			for (k=0; k<matrix["vowels"]["A"].length; k++) {
				if (matrix["vowels"]["A"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ă"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
			for (k=0; k<matrix["vowels"]["Â"].length; k++) {
				if (matrix["vowels"]["Â"][k] == lastChar) {
					incommingString = matrix["vowels"]["Ă"][k]
					canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
				}
			}
		break;
		case 9 :
			lastChar = begining.substr(begining.length-1, begining.length)
			if (lastChar == "d") {
				incommingString = "đ";
				canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
			} else if (lastChar == "D") {
				incommingString = "Đ";
				canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
			} else {
				switch(characterCase) {
					case 0 :
						insertString("đ");
					break;
					case 1 :
						insertString("Đ");
					break;
				}
				isAddingSpecialD = true;
			}
		break;
	}
}
function transformCharDoubleTap(begining, ending, who) {
	doubleTapAllowedToReplace = false;
	lastChar = begining.substr(begining.length-1, begining.length)
	if (lastChar == who) {
		for (k=0; k<matrix["doubleTap"].length; k++) {
			if (lastChar == matrix["doubleTap"][k]) {
				doubleTapAllowedToReplace = true;
			}
		}
	}
	if (doubleTapAllowedToReplace == true) {
		switch(who) {
			case "a" :
				incommingString = "ă";
			break;
			case "d" :
				incommingString = "đ";
			break;
			case "e" :
				incommingString = "ê";
			break;
			case "i" :
				incommingString = "ì";
			break;
			case "o" :
				incommingString = "ơ";
			break;
			case "u" :
				incommingString = "ư";
			break;
			case "y" :
				incommingString = "ỳ";
			break;
			case "A" :
				incommingString = "Ă";
			break;
			case "D" :
				incommingString = "Đ";
			break;
			case "E" :
				incommingString = "Ê";
			break;
			case "I" :
				incommingString = "Ì";
			break;
			case "O" :
				incommingString = "Ơ";
			break;
			case "U" :
				incommingString = "Ư";
			break;
			case "Y" :
				incommingString = "Ỳ";
			break;
		}
		canvas.value = begining.substr(0, begining.length-1) + incommingString + ending;
	}
	return doubleTapAllowedToReplace;
}