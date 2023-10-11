import sympy
for n in[*open(0)][1:]:a,b=sympy.factorint(n:=int(n));print(*[b,a][::1-(n%b**2>0)*2])