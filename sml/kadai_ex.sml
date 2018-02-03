fun compute s mapL =
    let
	fun EXP (h::t) =
          let
              val (v1, t1) = TERM (h::t)
          in
              if (hd t1) = "+" then
                  let
                      val (v2, t2) = TERM (tl t1)
                  in
                      (v1 + v2, t2)
                  end
              else if (hd t1) = "-" then
                  let
                      val (v3, t3) = TERM (tl t1)
                  in
                      (v1 - v3, t3)
                  end
              else
                  (v1, t1)
          end


	and TERM  (h::t) =
          let
              val (v1, t1) = BASE (h::t)
          in
              if (hd t1) = "*" then
                  let
                      val (v2, t2) = BASE (tl t1)
                  in
                      (v1 * v2, t2)
                  end
              else if (hd t1) = "/" then
                  let
                      val (v3, t3) = BASE (tl t1)
                  in
                      (v1 div v3, t3)
                  end
              else
                  (v1, t1)
          end





  and FUNC (h::t) =
          let
              val funcName = h
              val (v1,t1) = BASE t
          in
              if funcName = "fact" then
                  (fact v1, t1)
              else if funcName = "fibo" then
                  (fibo v1, t1)
              else
                  raise SyntaxError
          end

  and BASE (h::t) =
            if isInt h then (toInt h, t)
            else if isAlp h then (findValue h mapL, t)
            else if h = "(" then
              let
                  val (v1,t1) = EXP t
              in
                  if (hd t1) = ")" then
                      (v1, (tl t1))
                  else
                      raise SyntaxError
              end
            else if h = "fact" orelse h = "fibo" then FUNC (h::t)
            else raise SyntaxError


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
