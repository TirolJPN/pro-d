fun compute s mapL =
    let
	fun EXP nil = raise SyntaxError
          | EXP (h::t) =
            if isInt h then (toInt h, t)
            else if isAlp h then(findValue h mapL, t)
            else if h = "(" andalso isAlp (hd t) then FUNC (h::t)
            else if h = "(" andalso isOpr (hd t) then COMP (h::t)
            else raise SyntaxError

	and COMP nil = raise SyntaxError
          | COMP (h::t) =
            if (hd t) = "+" then
                let
                    val (v1,t1) = EXP (tl t)
                    val (v2,t2) = EXP t1
                in
                    if (hd t2) = ")" then
                        (v1 + v2, (tl t2))
                    else
                        raise SyntaxError
                end
            else if (hd t) = "-" then
                let
                    val (v1,t1) = EXP (tl t)
                    val (v2,t2) = EXP t1
                in
                    if (hd t2) = ")" then
                        (v1 - v2, (tl t2))
                    else
                        raise SyntaxError
                end
            else if (hd t) = "*" then
                let
                    val (v1,t1) = EXP (tl t)
                    val (v2,t2) = EXP t1
                in
                    if (hd t2) = ")" then
                        (v1 * v2, (tl t2))
                    else
                        raise SyntaxError
                end
            else if (hd t) = "/" then
                let
                    val (v1,t1) = EXP (tl t)
                    val (v2,t2) = EXP t1
                in
                    if (hd t2) = ")" then
                        (v1 div v2, (tl t2))
                    else
                        raise SyntaxError
                end
            else raise SyntaxError

  and FUNC nil = raise SyntaxError
          | FUNC (h::t) =
          let
              val funcName = (hd t)
              val exp = (tl t)
              val (v1,t1) = EXP exp
          in
              if funcName = "fact" andalso (hd t1) = ")" then
                  (fact v1,(tl t1))
              else if funcName = "fibo" andalso (hd t1) = ")" then
                  (fibo v1, (tl t1))
              else
                  raise SyntaxError
          end
  and findValue s nil = raise NotDefined
       | findValue s ((h:string*int)::t) =
          if s = #1(h) then
            #2(h)
          else findValue s t
    in
	let
            val (result,rest) = EXP (separate s)
	in
            if rest = nil then result else raise SyntaxError
	end
    end;
