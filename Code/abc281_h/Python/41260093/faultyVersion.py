import sys
readline=sys.stdin.readline

def Tonelli_Shanks(N,p):
    if pow(N,p>>1,p)==p-1:
        retu=None
    elif p%4==3:
        retu=pow(N,(p+1)//4,p)
    else:
        for nonresidue in range(1,p):
            if pow(nonresidue,p>>1,p)==p-1:
                break
        pp=p-1
        cnt=0
        while pp%2==0:
            pp//=2
            cnt+=1
        s=pow(N,pp,p)
        retu=pow(N,(pp+1)//2,p)
        for i in range(cnt-2,-1,-1):
            if pow(s,1<<i,p)==p-1:
                s*=pow(nonresidue,p>>1+i,p)
                s%=p
                retu*=pow(nonresidue,p>>2+i,p)
                retu%=p
    return retu

def Extended_Euclid(n,m):
    stack=[]
    while m:
        stack.append((n,m))
        n,m=m,n%m
    if n>=0:
        x,y=1,0
    else:
        x,y=-1,0
    for i in range(len(stack)-1,-1,-1):
        n,m=stack[i]
        x,y=y,x-(n//m)*y
    return x,y

class MOD:
    def __init__(self,p,e=None):
        self.p=p
        self.e=e
        if self.e==None:
            self.mod=self.p
        else:
            self.mod=self.p**self.e

    def Pow(self,a,n):
        a%=self.mod
        if n>=0:
            return pow(a,n,self.mod)
        else:
            #assert math.gcd(a,self.mod)==1
            x=Extended_Euclid(a,self.mod)[0]
            return pow(x,-n,self.mod)

    def Build_Fact(self,N):
        assert N>=0
        self.factorial=[1]
        if self.e==None:
            for i in range(1,N+1):
                self.factorial.append(self.factorial[-1]*i%self.mod)
        else:
            self.cnt=[0]*(N+1)
            for i in range(1,N+1):
                self.cnt[i]=self.cnt[i-1]
                ii=i
                while ii%self.p==0:
                    ii//=self.p
                    self.cnt[i]+=1
                self.factorial.append(self.factorial[-1]*ii%self.mod)
        self.factorial_inve=[None]*(N+1)
        self.factorial_inve[-1]=self.Pow(self.factorial[-1],-1)
        for i in range(N-1,-1,-1):
            ii=i+1
            while ii%self.p==0:
                ii//=self.p
            self.factorial_inve[i]=(self.factorial_inve[i+1]*ii)%self.mod

    def Build_Inverse(self,N):
        self.inverse=[None]*(N+1)
        assert self.p>N
        self.inverse[1]=1
        for n in range(2,N+1):
            if n%self.p==0:
                continue
            a,b=divmod(self.mod,n)
            self.inverse[n]=(-a*self.inverse[b])%self.mod
    
    def Inverse(self,n):
        return self.inverse[n]

    def Fact(self,N):
        if N<0:
            return 0
        retu=self.factorial[N]
        if self.e!=None and self.cnt[N]:
            retu*=pow(self.p,self.cnt[N],self.mod)%self.mod
            retu%=self.mod
        return retu

    def Fact_Inve(self,N):
        if self.e!=None and self.cnt[N]:
            return None
        return self.factorial_inve[N]

    def Comb(self,N,K,divisible_count=False):
        if K<0 or K>N:
            return 0
        retu=self.factorial[N]*self.factorial_inve[K]%self.mod*self.factorial_inve[N-K]%self.mod
        if self.e!=None:
            cnt=self.cnt[N]-self.cnt[N-K]-self.cnt[K]
            if divisible_count:
                return retu,cnt
            else:
                retu*=pow(self.p,cnt,self.mod)
                retu%=self.mod
        return retu

class Polynomial:
    def __init__(self,polynomial,max_degree=-1,eps=0,mod=0):
        self.max_degree=max_degree
        if self.max_degree!=-1 and len(polynomial)>self.max_degree+1:
            self.polynomial=polynomial[:self.max_degree+1]
        else:
            self.polynomial=polynomial
        self.mod=mod
        self.eps=eps

    def __eq__(self,other):
        if type(other)!=Polynomial:
            return False
        if len(self.polynomial)!=len(other.polynomial):
            return False
        for i in range(len(self.polynomial)):
            if self.eps<abs(self.polynomial[i]-other.polynomial[i]):
                return False
        return True

    def __ne__(self,other):
        if type(other)!=Polynomial:
            return True
        if len(self.polynomial)!=len(other.polynomial):
            return True
        for i in range(len(self.polynomial)):
            if self.eps<abs(self.polynomial[i]-other.polynomial[i]):
                return True
        return False

    def __add__(self,other):
        if type(other)==Polynomial:
            summ=[0]*max(len(self.polynomial),len(other.polynomial))
            for i in range(len(self.polynomial)):
                summ[i]+=self.polynomial[i]
            for i in range(len(other.polynomial)):
                summ[i]+=other.polynomial[i]
            if self.mod:
                for i in range(len(summ)):
                    summ[i]%=self.mod
        else:
            summ=[x for x in self.polynomial] if self.polynomial else [0]
            summ[0]+=other
            if self.mod:
                summ[0]%=self.mod
        while summ and abs(summ[-1])<=self.eps:
            summ.pop()
        summ=Polynomial(summ,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return summ

    def __sub__(self,other):
        if type(other)==Polynomial:
            diff=[0]*max(len(self.polynomial),len(other.polynomial))
            for i in range(len(self.polynomial)):
                diff[i]+=self.polynomial[i]
            for i in range(len(other.polynomial)):
                diff[i]-=other.polynomial[i]
            if self.mod:
                for i in range(len(diff)):
                    diff[i]%=self.mod
        else:
            diff=[x for x in self.polynomial] if self.polynomial else [0]
            diff[0]-=other
            if self.mod:
                diff[0]%=self.mod
        while diff and abs(diff[-1])<=self.eps:
            diff.pop()
        diff=Polynomial(diff,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return diff

    def __mul__(self,other):
        if type(other)==Polynomial:
            if self.max_degree==-1:
                prod=[0]*(len(self.polynomial)+len(other.polynomial)-1)
                for i in range(len(self.polynomial)):
                    for j in range(len(other.polynomial)):
                        prod[i+j]+=self.polynomial[i]*other.polynomial[j]
            else:
                prod=[0]*min(len(self.polynomial)+len(other.polynomial)-1,self.max_degree+1)
                for i in range(len(self.polynomial)):
                    for j in range(min(len(other.polynomial),self.max_degree+1-i)):
                        prod[i+j]+=self.polynomial[i]*other.polynomial[j]
            if self.mod:
                for i in range(len(prod)):
                    prod[i]%=self.mod
        else:
            if self.mod:
                prod=[x*other%self.mod for x in self.polynomial]
            else:
                prod=[x*other for x in self.polynomial]
        while prod and abs(prod[-1])<=self.eps:
            prod.pop()
        prod=Polynomial(prod,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return prod

    def __matmul__(self,other):
        assert type(other)==Polynomial
        if self.mod:
            prod=NTT(self.polynomial,other.polynomial)
        else:
            prod=FFT(self.polynomial,other.polynomial)
        if self.max_degree!=-1 and len(prod)>self.max_degree+1:
            prod=prod[:self.max_degree+1]
            while prod and abs(prod[-1])<=self.eps:
                prod.pop()
        prod=Polynomial(prod,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return prod

    def __pow__(self,other):
        if other==0:
            prod=Polynomial([1],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        elif other==1:
            prod=Polynomial([x for x in self.polynomial],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        else:
            prod=[1]
            doub=self.polynomial
            if self.mod:
                convolve=NTT
                convolve_Pow=NTT_Pow
            else:
                convolve=FFT
                convolve_Pow=FFT_Pow
            while other>=2:
                if other&1:
                    prod=convolve(prod,doub)
                    if self.max_degree!=-1:
                        prod=prod[:self.max_degree+1]
                doub=convolve_Pow(doub,2)
                if self.max_degree!=-1:
                    doub=doub[:self.max_degree+1]
                other>>=1
            prod=convolve(prod,doub)
            if self.max_degree!=-1:
                prod=prod[:self.max_degree+1]
            prod=Polynomial(prod,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return prod

    def __truediv__(self,other):
        if type(other)==Polynomial:
            assert other.polynomial
            for n in range(len(other.polynomial)):
                if self.eps<abs(other.polynomial[n]):
                    break
            assert len(self.polynomial)>n
            for i in range(n):
                assert abs(self.polynomial[i])<=self.eps
            self_polynomial=self.polynomial[n:]
            other_polynomial=other.polynomial[n:]
            if self.mod:
                inve=MOD(self.mod).Pow(other_polynomial[0],-1)
            else:
                inve=1/other_polynomial[0]
            quot=[]
            for i in range(len(self_polynomial)-len(other_polynomial)+1):
                if self.mod:
                    quot.append(self_polynomial[i]*inve%self.mod)
                else:
                    quot.append(self_polynomial[i]*inve)
                for j in range(len(other_polynomial)):
                    self_polynomial[i+j]-=other_polynomial[j]*quot[-1]
                    if self.mod:
                        self_polynomial[i+j]%=self.mod
            for i in range(max(0,len(self_polynomial)-len(other_polynomial)+1),len(self_polynomial)):
                if self.eps<abs(self_polynomial[i]):
                    assert self.max_degree!=-1
                    self_polynomial=self_polynomial[-len(other_polynomial)+1:]+[0]*(len(other_polynomial)-1-len(self_polynomial))
                    while len(quot)<=self.max_degree:
                        self_polynomial.append(0)
                        if self.mod:
                            quot.append(self_polynomial[0]*inve%self.mod)
                            self_polynomial=[(self_polynomial[i]-other_polynomial[i]*quot[-1])%self.mod for i in range(1,len(self_polynomial))]
                        else:
                            quot.append(self_polynomial[0]*inve)
                            self_polynomial=[(self_polynomial[i]-other_polynomial[i]*quot[-1]) for i in range(1,len(self_polynomial))]
                    break
            quot=Polynomial(quot,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        else:
            assert self.eps<abs(other)
            if self.mod:
                inve=MOD(self.mod).Pow(other,-1)
                quot=Polynomial([x*inve%self.mod for x in self.polynomial],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
            else:
                quot=Polynomial([x/other for x in self.polynomial],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return quot

    def __floordiv__(self,other):
        assert type(other)==Polynomial
        quot=[0]*(len(self.polynomial)-len(other.polynomial)+1)
        rema=[x for x in self.polynomial]
        if self.mod:
            inve=MOD(self.mod).Pow(other.polynomial[-1],-1)
            for i in range(len(self.polynomial)-len(other.polynomial),-1,-1):
                quot[i]=rema[i+len(other.polynomial)-1]*inve%self.mod
                for j in range(len(other.polynomial)):
                    rema[i+j]-=quot[i]*other.polynomial[j]
                    rema[i+j]%=self.mod
        else:
            inve=1/other.polynomial[-1]
            for i in range(len(self.polynomial)-len(other.polynomial),-1,-1):
                quot[i]=rema[i+len(other.polynomial)-1]*inve
                for j in range(len(other.polynomial)):
                    rema[i+j]-=quot[i]*other.polynomial[j]
        quot=Polynomial(quot,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return quot

    def __mod__(self,other):
        assert type(other)==Polynomial
        quot=[0]*(len(self.polynomial)-len(other.polynomial)+1)
        rema=[x for x in self.polynomial]
        if self.mod:
            inve=MOD(self.mod).Pow(other.polynomial[-1],-1)
            for i in range(len(self.polynomial)-len(other.polynomial),-1,-1):
                quot[i]=rema[i+len(other.polynomial)-1]*inve%self.mod
                for j in range(len(other.polynomial)):
                    rema[i+j]-=quot[i]*other.polynomial[j]
                    rema[i+j]%=self.mod
        else:
            inve=1/other.polynomial[-1]
            for i in range(len(self.polynomial)-len(other.polynomial),-1,-1):
                quot[i]=rema[i+len(other.polynomial)-1]*inve
                for j in range(len(other.polynomial)):
                    rema[i+j]-=quot[i]*other.polynomial[j]
        while rema and abs(rema[-1])<=self.eps:
            rema.pop()
        rema=Polynomial(rema,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return rema

    def __divmod__(self,other):
        assert type(other)==Polynomial
        quot=[0]*(len(self.polynomial)-len(other.polynomial)+1)
        rema=[x for x in self.polynomial]
        if self.mod:
            inve=MOD(self.mod).Pow(other.polynomial[-1],-1)
            for i in range(len(self.polynomial)-len(other.polynomial),-1,-1):
                quot[i]=rema[i+len(other.polynomial)-1]*inve%self.mod
                for j in range(len(other.polynomial)):
                    rema[i+j]-=quot[i]*other.polynomial[j]
                    rema[i+j]%=self.mod
        else:
            inve=1/other.polynomial[-1]
            for i in range(len(self.polynomial)-len(other.polynomial),-1,-1):
                quot[i]=rema[i+len(other.polynomial)-1]*inve
                for j in range(len(other.polynomial)):
                    rema[i+j]-=quot[i]*other.polynomial[j]
        while rema and abs(rema[-1])<=self.eps:
            rema.pop()
        quot=Polynomial(quot,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        rema=Polynomial(rema,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return quot,rema

    def __neg__(self):
        if self.mod:
            nega=Polynomial([(-x)%self.mod for x in self.polynomial],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        else:
            nega=Polynomial([-x for x in self.polynomial],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return nega

    def __pos__(self):
        posi=Polynomial([x for x in self.polynomial],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return posi

    def __bool__(self):
        return self.polynomial

    def __getitem__(self,n):
        if type(n)==int:
            if n<=len(self.polynomial)-1:
                return self.polynomial[n]
            else:
                return 0
        else:
            return Polynomial(polynomial=self.polynomial[n],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
    
    def __setitem__(self,n,a):
        if self.mod:
            a%=self.mod
        if self.max_degree==-1 or n<=self.max_degree:
            if n<=len(self.polynomial)-1:
                self.polynomial[n]=a
            elif self.eps<abs(a):
                self.polynomial+=[0]*(n-len(self.polynomial))+[a]

    def __iter__(self):
        for x in self.polynomial:
            yield x

    def __call__(self,x):
        retu=0
        pow_x=1
        for i in range(len(self.polynomial)):
            retu+=pow_x*self.polynomial[i]
            pow_x*=x
            if self.mod:
                retu%=self.mod
                pow_x%=self.mod
        return retu

    def __str__(self):
        return "["+", ".join(map(str,self.polynomial))+"]"

    def __len__(self):
        return len(self.polynomial)

    def differentiate(self):
        if self.mod:
            differential=[x*i%self.mod for i,x in enumerate(self.polynomial[1:],1)]
        else:
            differential=[x*i for i,x in enumerate(self.polynomial[1:],1)]
        return Polynomial(differential,max_degree=self.max_degree,eps=self.eps,mod=self.mod)

    def integrate(self):
        if self.mod:
            integral=[0]+[x*MOD(mod).Pow(i+1,-1)%self.mod for i,x in enumerate(self.polynomial)]
        else:
            integral=[0]+[x/(i+1) for i,x in enumerate(self.polynomial)]
        while integral and abs(integral[-1])<=self.eps:
            integral.pop()
        return Polynomial(integral,max_degree=self.max_degree,eps=self.eps,mod=self.mod)

    def inverse(self):
        assert self.polynomial and self.eps<self.polynomial[0]
        assert self.max_degree!=-1
        if self.mod:
            quot=[MOD(self.mod).Pow(self.polynomial[0],-1)]
            if self.mod==998244353:
                prim_root=3
                prim_root_inve=332748118
            else:
                prim_root=Primitive_Root(self.mod)
                prim_root_inve=MOD(self.mod).Pow(prim_root,-1)
            def DFT(polynomial,n,inverse=False):
                polynomial=polynomial+[0]*((1<<n)-len(polynomial))
                if inverse:
                    for bit in range(1,n+1):
                        a=1<<bit-1
                        x=pow(prim_root,self.mod-1>>bit,self.mod)
                        U=[1]
                        for _ in range(a):
                            U.append(U[-1]*x%self.mod)
                        for i in range(1<<n-bit):
                            for j in range(a):
                                s=i*2*a+j
                                t=s+a
                                polynomial[s],polynomial[t]=(polynomial[s]+polynomial[t]*U[j])%self.mod,(polynomial[s]-polynomial[t]*U[j])%self.mod
                    x=pow((self.mod+1)//2,n,self.mod)
                    for i in range(1<<n):
                        polynomial[i]*=x
                        polynomial[i]%=self.mod
                else:
                    for bit in range(n,0,-1):
                        a=1<<bit-1
                        x=pow(prim_root_inve,self.mod-1>>bit,self.mod)
                        U=[1]
                        for _ in range(a):
                            U.append(U[-1]*x%self.mod)
                        for i in range(1<<n-bit):
                            for j in range(a):
                                s=i*2*a+j
                                t=s+a
                                polynomial[s],polynomial[t]=(polynomial[s]+polynomial[t])%self.mod,U[j]*(polynomial[s]-polynomial[t])%self.mod
                return polynomial
        else:
            quot=[1/self.polynomial[0]]
            def DFT(polynomial,n,inverse=False):
                N=len(polynomial)
                if inverse:
                    primitive_root=[math.cos(-i*2*math.pi/(1<<n))+math.sin(-i*2*math.pi/(1<<n))*1j for i in range(1<<n)]
                else:
                    primitive_root=[math.cos(i*2*math.pi/(1<<n))+math.sin(i*2*math.pi/(1<<n))*1j for i in range(1<<n)]
                polynomial=polynomial+[0]*((1<<n)-N)
                if inverse:
                    for bit in range(1,n+1):
                        a=1<<bit-1
                        for i in range(1<<n-bit):
                            for j in range(a):
                                s=i*2*a+j
                                t=s+a
                                polynomial[s],polynomial[t]=polynomial[s]+polynomial[t]*primitive_root[j<<n-bit],polynomial[s]-polynomial[t]*primitive_root[j<<n-bit]
                    for i in range(1<<n):
                        polynomial[i]=round((polynomial[i]/(1<<n)).real)
                else:
                    for bit in range(n,0,-1):
                        a=1<<bit-1
                        for i in range(1<<n-bit):
                            for j in range(a):
                                s=i*2*a+j
                                t=s+a
                                polynomial[s],polynomial[t]=polynomial[s]+polynomial[t],primitive_root[j<<n-bit]*(polynomial[s]-polynomial[t])

                return polynomial
        for n in range(self.max_degree.bit_length()):
            prev=quot
            DFT_prev=DFT(prev,n+1)
            if self.mod:
                quot=[x*y%self.mod for x,y in zip(DFT_prev,DFT(self.polynomial[:1<<n+1],n+1))]
            else:
                quot=[x*y for x,y in zip(DFT_prev,DFT(self.polynomial[:1<<n+1],n+1))]
            quot=DFT([0]*(1<<n)+DFT(quot,n+1,inverse=True)[1<<n:],n+1)
            if self.mod:
                quot=[(-x*y)%self.mod for x,y in zip(DFT_prev,quot)]
            else:
                quot=[-x*y for x,y in zip(DFT_prev,quot)]
            quot=prev+DFT(quot,n+1,inverse=True)[1<<n:]
        quot=quot[:self.max_degree+1]
        quot=Polynomial(quot,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return quot

    def log(self):
        assert self.max_degree!=-1
        assert self.polynomial and abs(self.polynomial[0]-1)<=self.eps
        log=self.inverse()
        if self.mod:
            log=Polynomial(NTT(self.differentiate().polynomial,log.polynomial),max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        else:
            log=Polynomial(FFT(self.differentiate().polynomial,log.polynomial),max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        log=log.integrate()
        return log

    def Newton(self,n0,f,differentiated_f=None):
        newton=[n0]
        while len(newton)<self.max_degree+1:
            prev=newton
            if differentiated_f==None:
                newton=f(prev,self.polynomial)
            else:
                newton=f(prev)
                for i in range(min(len(self.polynomial),len(newton))):
                    newton[i]-=self.polynomial[i]
                    newton[i]%=self.mod
                if self.mod:
                    newton=NTT(newton,Polynomial(differentiated_f(prev),max_degree=len(newton)-1,eps=self.eps,mod=self.mod).inverse().polynomial)[:len(newton)]
                else:
                    newton=FFT(newton,Polynomial(differentiated_f(prev),max_degree=len(newton)-1,eps=self.eps,mod=self.mod).inverse().polynomial)[:len(newton)]
            for i in range(len(newton)):
                newton[i]=-newton[i]
                newton[i]%=self.mod
            for i in range(len(prev)):
                newton[i]+=prev[i]
                newton[i]%=self.mod
        newton=newton[:self.max_degree+1]
        while newton and newton[-1]<=self.eps:
            newton.pop()
        return Polynomial(newton,max_degree=self.max_degree,eps=self.eps,mod=self.mod)

    def sqrt(self):
        if self.polynomial:
            for cnt0 in range(len(self.polynomial)):
                if self.polynomial[cnt0]:
                    break
            if cnt0%2:
                sqrt=None
            else:
                if self.mod:
                    n0=Tonelli_Shanks(self.polynomial[cnt0],self.mod)
                else:
                    if self.polynomial[cnt0]>=self.eps:
                        n0=self.polynomial[cnt0]**.5
                if n0==None:
                    sqrt=None
                else:
                    def f(prev):
                        if self.mod:
                            return NTT_Pow(prev,2)+[0]
                        else:
                            return FFT_Pow(prev,2)+[0]
                    def differentiated_f(prev):
                        retu=[0]*(2*len(prev)-1)
                        for i in range(len(prev)):
                            retu[i]+=2*prev[i]
                            if self.mod:
                                retu[i]%self.mod
                        return retu
                    sqrt=[0]*(cnt0//2)+Polynomial(self.polynomial[cnt0:],max_degree=self.max_degree-cnt0//2,mod=self.mod).Newton(n0,f,differentiated_f).polynomial
                    sqrt=Polynomial(sqrt,max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        else:
            sqrt=Polynomial([],max_degree=self.max_degree,eps=self.eps,mod=self.mod)
        return sqrt

    def exp(self):
        assert not self.polynomial or abs(self.polynomial[0])<=self.eps
        def f(prev,poly):
            newton=Polynomial(prev,max_degree=2*len(prev)-1,eps=self.eps,mod=self.mod).log().polynomial
            newton+=[0]*(2*len(prev)-len(newton))
            for i in range(min(len(poly),len(newton))):
                newton[i]-=poly[i]
            if self.mod:
                for i in range(len(newton)):
                    newton[i]%=self.mod
            if self.mod:
                return NTT(prev,newton)[:2*len(prev)]
            else:
                return FFT(prev,newton)[:2*len(prev)]
        return Polynomial(self.polynomial,max_degree=self.max_degree,mod=self.mod).Newton(1,f)

    def Degree(self):
        return len(self.polynomial)-1

def Hadamard(polynomial,n,mod=0,inverse=False):
    polynomial_=[x for x in polynomial]+[0]*((1<<n)-len(polynomial))
    for bit in range(n):
        for i in range(1<<n):
            ii=i^(1<<bit)
            if i>ii:
                continue
            polynomial_[i],polynomial_[ii]=polynomial_[i]+polynomial_[ii],polynomial_[i]-polynomial_[ii]
            if mod:
                polynomial_[i]%=mod
                polynomial_[ii]%=mod
    if inverse:
        if mod:
            inve_2=pow((mod+1)//2,n)
            for i in range(1<<n):
                polynomial_[i]*=inve_2
                polynomial_[i]%=mod
        else:
            pow_2=pow(2,n)
            for i in range(1<<n):
                polynomial_[i]/=pow_2
    return polynomial_

def XOR_Convolution(polynomial0,polynomial1,mod=0):
    n=(max(len(polynomial0),len(polynomial1))-1).bit_length()
    Hadamard_polynomial0=Hadamard(polynomial0,n,mod=mod)
    Hadamard_polynomial1=Hadamard(polynomial1,n,mod=mod)
    if mod:
        convolution=[x*y%mod for x,y in zip(Hadamard_polynomial0,Hadamard_polynomial1)]
    else:
        convolution=[x*y for x,y in zip(Hadamard_polynomial0,Hadamard_polynomial1)]
    convolution=Hadamard(convolution,n,mod=mod,inverse=True)
    return convolution

def Bostan_Mori(poly_nume,poly_deno,N,mod=0,convolve=None):
    if type(poly_nume)==Polynomial:
        poly_nume=poly_nume.polynomial
    if type(poly_deno)==Polynomial:
        poly_deno=poly_deno.polynomial
    if convolve==None:
        def convolve(poly_nume,poly_deno):
            conv=[0]*(len(poly_nume)+len(poly_deno)-1)
            for i in range(len(poly_nume)):
                for j in range(len(poly_deno)):
                    x=poly_nume[i]*poly_deno[j]
                    if mod:
                        x%=mod
                    conv[i+j]+=x
            if mod:
                for i in range(len(conv)):
                    conv[i]%=mod
            return conv
    while N:
        poly_deno_=[-x if i%2 else x for i,x in enumerate(poly_deno)]
        if N%2:
            poly_nume=convolve(poly_nume,poly_deno_)[1::2]
        else:
            poly_nume=convolve(poly_nume,poly_deno_)[::2]
        poly_deno=convolve(poly_deno,poly_deno_)[::2]
        if mod:
            for i in range(len(poly_nume)):
                poly_nume[i]%=mod
            for i in range(len(poly_deno)):
                poly_deno[i]%=mod
        N//=2
    return poly_nume[0]

#mod = 998244353
imag = 911660635
iimag = 86583718
rate2 = (911660635, 509520358, 369330050, 332049552, 983190778, 123842337, 238493703, 975955924, 603855026, 856644456, 131300601,
              842657263, 730768835, 942482514, 806263778, 151565301, 510815449, 503497456, 743006876, 741047443, 56250497, 867605899)
irate2 = (86583718, 372528824, 373294451, 645684063, 112220581, 692852209, 155456985, 797128860, 90816748, 860285882, 927414960,
               354738543, 109331171, 293255632, 535113200, 308540755, 121186627, 608385704, 438932459, 359477183, 824071951, 103369235)
rate3 = (372528824, 337190230, 454590761, 816400692, 578227951, 180142363, 83780245, 6597683, 70046822, 623238099,
              183021267, 402682409, 631680428, 344509872, 689220186, 365017329, 774342554, 729444058, 102986190, 128751033, 395565204)
irate3 = (509520358, 929031873, 170256584, 839780419, 282974284, 395914482, 444904435, 72135471, 638914820, 66769500,
               771127074, 985925487, 262319669, 262341272, 625870173, 768022760, 859816005, 914661783, 430819711, 272774365, 530924681)

def butterfly(a):
    n = len(a)
    h = (n - 1).bit_length()
    len_ = 0
    while len_ < h:
        if h - len_ == 1:
            p = 1 << (h - len_ - 1)
            rot = 1
            for s in range(1 << len_):
                offset = s << (h - len_)
                for i in range(p):
                    l = a[i + offset]
                    r = a[i + offset + p] * rot % mod
                    a[i + offset] = (l + r) % mod
                    a[i + offset + p] = (l - r) % mod
                if s + 1 != 1 << len_:
                    rot *= rate2[(~s & -~s).bit_length() - 1]
                    rot %= mod
            len_ += 1
        else:
            p = 1 << (h - len_ - 2)
            rot = 1
            for s in range(1 << len_):
                rot2 = rot * rot % mod
                rot3 = rot2 * rot % mod
                offset = s << (h - len_)
                for i in range(p):
                    a0 = a[i + offset]
                    a1 = a[i + offset + p] * rot
                    a2 = a[i + offset + p * 2] * rot2
                    a3 = a[i + offset + p * 3] * rot3
                    a1na3imag = (a1 - a3) % mod * imag
                    a[i + offset] = (a0 + a2 + a1 + a3) % mod
                    a[i + offset + p] = (a0 + a2 - a1 - a3) % mod
                    a[i + offset + p * 2] = (a0 - a2 + a1na3imag) % mod
                    a[i + offset + p * 3] = (a0 - a2 - a1na3imag) % mod
                if s + 1 != 1 << len_:
                    rot *= rate3[(~s & -~s).bit_length() - 1]
                    rot %= mod
            len_ += 2

def butterfly_inv(a):
    n = len(a)
    h = (n - 1).bit_length()
    len_ = h
    while len_:
        if len_ == 1:
            p = 1 << (h - len_)
            irot = 1
            for s in range(1 << (len_ - 1)):
                offset = s << (h - len_ + 1)
                for i in range(p):
                    l = a[i + offset]
                    r = a[i + offset + p]
                    a[i + offset] = (l + r) % mod
                    a[i + offset + p] = (l - r) * irot % mod
                if s + 1 != (1 << (len_ - 1)):
                    irot *= irate2[(~s & -~s).bit_length() - 1]
                    irot %= mod
            len_ -= 1
        else:
            p = 1 << (h - len_)
            irot = 1
            for s in range(1 << (len_ - 2)):
                irot2 = irot * irot % mod
                irot3 = irot2 * irot % mod
                offset = s << (h - len_ + 2)
                for i in range(p):
                    a0 = a[i + offset]
                    a1 = a[i + offset + p]
                    a2 = a[i + offset + p * 2]
                    a3 = a[i + offset + p * 3]
                    a2na3iimag = (a2 - a3) * iimag % mod
                    a[i + offset] = (a0 + a1 + a2 + a3) % mod
                    a[i + offset + p] = (a0 - a1 + a2na3iimag) * irot % mod
                    a[i + offset + p * 2] = (a0 + a1 - a2 - a3) * irot2 % mod
                    a[i + offset + p * 3] = (a0 - a1 - a2na3iimag) * irot3 % mod
                if s + 1 != (1 << (len_ - 2)):
                    irot *= irate3[(~s & -~s).bit_length() - 1]
                    irot %= mod
            len_ -= 2

def integrate(a):
    a=a.copy()
    n = len(a)
    assert n > 0
    a.pop()
    a.insert(0, 0)
    inv = [1, 1]
    for i in range(2, n):
        inv.append(-inv[mod%i] * (mod//i) % mod)
        a[i] = a[i] * inv[i] % mod
    return a

def differentiate(a):
    n = len(a)
    assert n > 0
    for i in range(2, n):
        a[i] = a[i] * i % mod
    a.pop(0)
    a.append(0)
    return a

def convolution_naive(a, b):
    n = len(a)
    m = len(b)
    ans = [0] * (n + m - 1)
    if n < m:
        for j in range(m):
            for i in range(n):
                ans[i + j] = (ans[i + j] + a[i] * b[j]) % mod
    else:
        for i in range(n):
            for j in range(m):
                ans[i + j] = (ans[i + j] + a[i] * b[j]) % mod
    return ans

def convolution_ntt(a, b):
    a = a.copy()
    b = b.copy()
    n = len(a)
    m = len(b)
    z = 1 << (n + m - 2).bit_length()
    a += [0] * (z - n)
    butterfly(a)
    b += [0] * (z - m)
    butterfly(b)
    for i in range(z):
        a[i] = a[i] * b[i] % mod
    butterfly_inv(a)
    a = a[:n + m - 1]
    iz = pow(z, mod - 2, mod)
    for i in range(n + m - 1):
        a[i] = a[i] * iz % mod
    return a

def convolution_square(a):
    a = a.copy()
    n = len(a)
    z = 1 << (2 * n - 2).bit_length()
    a += [0] * (z - n)
    butterfly(a)
    for i in range(z):
        a[i] = a[i] * a[i] % mod
    butterfly_inv(a)
    a = a[:2 * n - 1]
    iz = pow(z, mod - 2, mod)
    for i in range(2 * n - 1):
        a[i] = a[i] * iz % mod
    return a

def convolution(a, b):
    """It calculates (+, x) convolution in mod 998244353. 
    Given two arrays a[0], a[1], ..., a[n - 1] and b[0], b[1], ..., b[m - 1], 
    it calculates the array c of length n + m - 1, defined by

    >   c[i] = sum(a[j] * b[i - j] for j in range(i + 1)) % 998244353.

    It returns an empty list if at least one of a and b are empty.

    Complexity
    ----------

    >   O(n log n), where n = len(a) + len(b).
    """
    n = len(a)
    m = len(b)
    if n == 0 or m == 0:
        return []
    if min(n, m) <= 60:
        return convolution_naive(a, b)
    if a is b:
        return convolution_square(a)
    return convolution_ntt(a, b)

def inverse(a):
    n = len(a)
    assert n > 0 and a[0] != 0
    res = [pow(a[0], mod - 2, mod)]
    m = 1
    while m < n:
        f = a[:min(n,2*m)] + [0]*(2*m-min(n,2*m))
        g = res + [0]*m
        butterfly(f)
        butterfly(g)
        for i in range(2*m):
            f[i] = f[i] * g[i] % mod
        butterfly_inv(f)
        f = f[m:] + [0]*m
        butterfly(f)
        for i in range(2*m):
            f[i] = f[i] * g[i] % mod
        butterfly_inv(f)
        iz = pow(2*m, mod-2, mod)
        iz = (-iz*iz) % mod
        for i in range(m):
            f[i] = f[i] * iz % mod
        res += f[:m]
        m <<= 1
    return res[:n]

def log(a):
    a = a.copy()
    n = len(a)
    assert n > 0 and a[0] == 1
    a_inv = inverse(a)
    a=differentiate(a)
    a = convolution(a, a_inv)[:n]
    a=integrate(a)
    return a

def exp(a):
    a = a.copy()
    n = len(a)
    assert n > 0 and a[0] == 0
    g = [1]
    a[0] = 1
    h_drv = a.copy()
    h_drv=differentiate(h_drv)
    m = 1
    while m < n:
        f_fft = a[:m] + [0] * m
        butterfly(f_fft)

        if m > 1:
            _f = [f_fft[i] * g_fft[i] % mod for i in range(m)]
            butterfly_inv(_f)
            _f = _f[m // 2:] + [0] * (m // 2)
            butterfly(_f)
            for i in range(m):
                _f[i] = _f[i] * g_fft[i] % mod
            butterfly_inv(_f)
            _f = _f[:m//2]
            iz = pow(m, mod - 2, mod)
            iz *= -iz
            iz %= mod
            for i in range(m//2):
                _f[i] = _f[i] * iz % mod
            g.extend(_f)

        t = a[:m]
        t=differentiate(t)
        r = h_drv[:m - 1]
        r.append(0)
        butterfly(r)
        for i in range(m):
            r[i] = r[i] * f_fft[i] % mod
        butterfly_inv(r)
        im = pow(-m, mod - 2, mod)
        for i in range(m):
            r[i] = r[i] * im % mod
        for i in range(m):
            t[i] = (t[i] + r[i]) % mod
        t = [t[-1]] + t[:-1]

        t += [0] * m
        butterfly(t)
        g_fft = g + [0] * (2 * m - len(g))
        butterfly(g_fft)
        for i in range(2 * m):
            t[i] = t[i] * g_fft[i] % mod
        butterfly_inv(t)
        t = t[:m]
        i2m = pow(2 * m, mod - 2, mod)
        for i in range(m):
            t[i] = t[i] * i2m % mod
    
        v = a[m:min(n, 2 * m)]
        v += [0] * (m - len(v))
        t = [0] * (m - 1) + t + [0]
        t=integrate(t)
        for i in range(m):
            v[i] = (v[i] - t[m + i]) % mod

        v += [0] * m
        butterfly(v)
        for i in range(2 * m):
            v[i] = v[i] * f_fft[i] % mod
        butterfly_inv(v)
        v = v[:m]
        i2m = pow(2 * m, mod - 2, mod)
        for i in range(m):
            v[i] = v[i] * i2m % mod
        
        for i in range(min(n - m, m)):
            a[m + i] = v[i]
        
        m *= 2
    return a

def power(a,k):
    n = len(a)
    assert n>0
    if k==0:
        return [1]+[0]*(n-1)
    l = 0
    while l < len(a) and not a[l]:
        l += 1
    if l * k >= n:
        return [0] * n
    ic = pow(a[l], mod - 2, mod)
    pc = pow(a[l], k, mod)
    a = log([a[i] * ic % mod for i in range(l, len(a))])
    for i in range(len(a)):
        a[i] = a[i] * k % mod
    a = exp(a)
    for i in range(len(a)):
        a[i] = a[i] * pc % mod
    a = [0] * (l * k) + a[:n - l * k]
    return a

def sqrt(a):
    if len(a) == 0:
        return []
    if a[0] == 0:
        for d in range(1, len(a)):
            if a[d]:
                if d & 1:
                    return None
                if len(a) - 1 < d // 2:
                    break
                res=sqrt(a[d:]+[0]*(d//2))
                if res == None:
                    return None
                res = [0]*(d//2)+res
                return res
        return [0]*len(a)
    
    sqr = Tonelli_Shanks(a[0],mod)
    if sqr == None:
        return None
    T = [0] * (len(a))
    T[0] = sqr
    res = T.copy()
    T[0] = pow(sqr,mod-2,mod) #T:res^{-1}
    m = 1
    two_inv = (mod + 1) // 2
    F = [sqr]
    while m <= len(a) - 1:
        for i in range(m):
            F[i] *= F[i]
            F[i] %= mod
        butterfly_inv(F)
        iz = pow(m, mod-2, mod)
        for i in range(m):
            F[i] = F[i] * iz % mod
        delta = [0] * (2 * m)
        for i in range(m):
            delta[i + m] = F[i] - a[i] - (a[i + m] if i+m<len(a) else 0)
        butterfly(delta)
        G = [0] * (2 * m)
        for i in range(m):
            G[i] = T[i]
        butterfly(G)
        for i in range(2 * m):
            delta[i] *= G[i]
            delta[i] %= mod
        butterfly_inv(delta)
        iz = pow(2*m, mod-2, mod)
        for i in range(2*m):
            delta[i] = delta[i] * iz % mod
        for i in range(m, min(2 * m, len(a))):
            res[i] = -delta[i] * two_inv%mod
            res[i]%=mod
        if 2 * m > len(a) - 1:
            break
        F = res[:2 * m]
        butterfly(F)
        eps = [F[i] * G[i] % mod for i in range(2 * m)]
        butterfly_inv(eps)
        for i in range(m):
            eps[i] = 0
        iz = pow(2*m, mod-2, mod)
        for i in range(m,2*m):
            eps[i] = eps[i] * iz % mod
        butterfly(eps)
        for i in range(2 * m):
            eps[i] *= G[i]
            eps[i] %= mod
        butterfly_inv(eps)
        for i in range(m, 2 * m):
            T[i] = -eps[i]*iz
            T[i]%=mod
        iz = iz*iz % mod

        m <<= 1
    return res

def taylor_shift(a,c):
    a=a.copy()
    n=len(a)
    MD=MOD(mod)
    MD.Build_Fact(n-1)
    for i in range(n):
        a[i]*=MD.Fact(i)
        a[i]%=mod
    C=[1]
    for i in range(1,n):
        C.append(C[-1]*c%mod)
    for i in range(n):
        C[i]*=MD.Fact_Inve(i)
        C[i]%=mod
    a=convolution(a,C[::-1])[n-1:]
    for i in range(n):
        a[i]*=MD.Fact_Inve(i)
        a[i]%=mod
    return a

def division_modulus(f,g):
    n=len(f)
    m=len(g)
    while m and g[m-1]==0:
        m-=1
    assert m
    if n>=m:
        fR=f[::-1][:n-m+1]
        gR=g[:m][::-1][:n-m+1]+[0]*max(0,n-m+1-m)
        qR=convolution(fR,inverse(gR))[:n-m+1]
        q=qR[::-1]
        r=[(f[i]-x)%mod for i,x in enumerate(convolution(g,q)[:m-1])]
        while r and r[-1]==0:
            r.pop()
    else:
        q,r=[],f.copy()
    return q,r

def multipoint_evaluation(f, x):
    n = len(x)
    sz = 1 << (n - 1).bit_length()
    g = [[1] for _ in range(2 * sz)]
    for i in range(n):
        g[i + sz] = [-x[i], 1]
    for i in range(1, sz)[::-1]:
        g[i] = convolution(g[2 * i],g[2 * i + 1])
    g[1] =division_modulus(f,g[1])[1]
    for i in range(2, 2 * sz):
        g[i]=division_modulus(g[i>>1],g[i])[1]
    res = [g[i + sz][0] if g[i+sz] else 0 for i in range(n)]
    return res

N,A=map(int,readline().split())
mod=998244353
MD=MOD(mod)
MD.Build_Fact(N)
f=[1]
for i in range(1,N+1):
    f.append(f[i-1]*(A-i+1)%mod)
for i in range(N+1):
    f[i]*=MD.Fact_Inve(i)
    f[i]%=mod
ans_lst=[None]*N
ans_lst[0]=A
def solve(l,r,poly):
    if l==r:
        return [1]
    if l+1==r:
        ans_lst[l-1]=poly[0]
        return [1,ans_lst[l-1]]
    mid=(l+r)//2
    P=solve(l,mid,poly[:mid-l])
    P=convolution(P,solve(mid,r,convolution(poly,P)[mid-l:r-l]))
    return P
solve(2,N+1,f[2:N+1])
ans=ans_lst[N-1]
print(ans)