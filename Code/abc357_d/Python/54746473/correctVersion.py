def Arithmetic_Sequence_Sum(N,first_term,common_ratio,mod):
    return first_term*(Matrix(matrix=[[common_ratio,1],[0,1]],mod=mod)@N)[0][1]%mod

class Matrix:
    def __init__(self,H=0,W=0,matrix=False,eps=0,mod=0,identity=0):
        if identity:
            if H:
                self.H=H
                self.W=H
            else:
                self.H=W
                self.W=W
            self.matrix=[[0]*self.W for i in range(self.H)]
            for i in range(self.H):
                self.matrix[i][i]=identity
        elif matrix:
            self.matrix=matrix
            self.H=len(self.matrix)
            self.W=len(self.matrix[0]) if self.matrix else 0
        else:
            self.H=H
            self.W=W
            self.matrix=[[0]*self.W for i in range(self.H)]
        self.mod=mod
        self.eps=eps

    def __eq__(self,other):
        if type(other)!=Matrix:
            return False
        if self.H!=other.H:
            return False
        if self.mod:
            for i in range(self.H):
                for j in range(self.W):
                    if self.matrix[i][j]%self.mod!=other.matrix[i][j]%self.mod:
                        return False
        else:
            for i in range(self.H):
                for j in range(self.W):
                    if self.eps<abs(self.matrix[i][j]-other.matrix[i][j]):
                        return False
        return True

    def __ne__(self,other):
        if type(other)!=Matrix:
            return True
        if self.H!=other.H:
            return True
        if self.mod:
            for i in range(self.H):
                for j in range(self.W):
                    if self.matrix[i][j]%self.mod!=other.matrix[i][j]%self.mod:
                        return True
        else:
            for i in range(self.H):
                for j in range(self.W):
                    if self.eps<abs(self.matrix[i][j]-other.matrix[i][j]):
                        return True
        return False

    def __add__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            if self.mod:
                summ=Matrix(matrix=[[(self.matrix[i][j]+other.matrix[i][j])%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                summ=Matrix(matrix=[[self.matrix[i][j]+other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            if self.mod:
                summ=Matrix(matrix=[[(self.matrix[i][j]+other)%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                summ=Matrix(matrix=[[self.matrix[i][j]+other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return summ

    def __sub__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            if self.mod:
                diff=Matrix(matrix=[[(self.matrix[i][j]-other.matrix[i][j])%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                diff=Matrix(matrix=[[self.matrix[i][j]-other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            if self.mod:
                diff=Matrix(matrix=[[(self.matrix[i][j]-other)%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                diff=Matrix(matrix=[[self.matrix[i][j]-other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return diff

    def __mul__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            if self.mod:
                prod=Matrix(matrix=[[(self.matrix[i][j]*other.matrix[i][j])%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                prod=Matrix(matrix=[[self.matrix[i][j]*other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            if self.mod:
                prod=Matrix(matrix=[[(self.matrix[i][j]*other)%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                prod=Matrix(matrix=[[self.matrix[i][j]*other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return prod

    def __matmul__(self,other):
        if type(other)==Matrix:
            assert self.W==other.H
            prod=Matrix(H=self.H,W=other.W,eps=self.eps,mod=self.mod)
            for i in range(self.H):
                for j in range(other.W):
                    for k in range(self.W):
                        prod.matrix[i][j]+=self.matrix[i][k]*other.matrix[k][j]
                        if self.mod:
                            prod.matrix[i][j]%=self.mod
        elif type(other)==int:
            assert self.H==self.W
            if other==0:
                prod=Matrix(H=self.H,eps=self.eps,mod=self.mod,identity=1)
            elif other==1:
                prod=Matrix(matrix=[[self.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                prod=Matrix(H=self.H,eps=self.eps,mod=self.mod,identity=1)
                doub=Matrix(matrix=[[self.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
                while other>=2:
                    if other&1:
                        prod@=doub
                    doub@=doub
                    other>>=1
                prod@=doub
        return prod

    def __truediv__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            if self.mod:
                quot=Matrix(matrix=[[(self.matrix[i][j]*MOD(self.mod).Pow(other.matrix[i][j],-1))%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                quot=Matrix(matrix=[[self.matrix[i][j]/other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            if self.mod:
                inve=MOD(self.mod).Pow(other,-1)
                quot=Matrix(matrix=[[(self.matrix[i][j]*inve)%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                quot=Matrix(matrix=[[self.matrix[i][j]/other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return quot

    def __floordiv__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            quot=Matrix(matrix=[[self.matrix[i][j]//other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            quot=Matrix(matrix=[[self.matrix[i][j]//other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return quot

    def __mod__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            rema=Matrix(matrix=[[self.matrix[i][j]%other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            rema=Matrix(matrix=[[self.matrix[i][j]%other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return rema

    def __pow__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            if self.mod:
                powe=Matrix(matrix=[[pow(self.matrix[i][j],other.matrix[i][j],self.mod) for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                powe=Matrix(matrix=[[pow(self.matrix[i][j],other.matrix[i][j]) for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            if self.mod:
                powe=Matrix(matrix=[[pow(self.matrix[i][j],other,self.mod) for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                powe=Matrix(matrix=[[pow(self.matrix[i][j],other) for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return powe

    def __lshift__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            lshi=Matrix(matrix=[[self.matrix[i][j]<<other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            lshi=Matrix(matrix=[[self.matrix[i][j]<<other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return lshi

    def __rshift__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            rshi=Matrix(matrix=[[self.matrix[i][j]>>other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            rshi=Matrix(matrix=[[self.matrix[i][j]>>other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return rshi

    def __and__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            conj=Matrix(matrix=[[self.matrix[i][j]&other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            conj=Matrix(matrix=[[self.matrix[i][j]&other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return conj

    def __or__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            disj=Matrix(matrix=[[self.matrix[i][j]|other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            disj=Matrix(matrix=[[self.matrix[i][j]|other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return disj

    def __xor__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            excl=Matrix(matrix=[[self.matrix[i][j]^other.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            excl=Matrix(matrix=[[self.matrix[i][j]^other for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return excl

    def __iadd__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]+=other.matrix[i][j]
                    if self.mod:
                        self.matrix[i][j]%=self.mod
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]+=other
                    if self.mod:
                        self.matrix[i][j]%=self.mod
        return self

    def __isub__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]-=other.matrix[i][j]
                    if self.mod:
                        self.matrix[i][j]%=self.mod
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]-=other
                    if self.mod:
                        self.matrix[i][j]%=self.mod
        return self

    def __imul__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]*=other.matrix[i][j]
                    if self.mod:
                        self.matrix[i][j]%=self.mod
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]*=other
                    if self.mod:
                        self.matrix[i][j]%=self.mod
        return self

    def __imatmul__(self,other):
        if type(other)==Matrix:
            assert self.W==other.H
            prod=Matrix(H=self.H,W=other.W,eps=self.eps,mod=self.mod)
            for i in range(self.H):
                for j in range(other.W):
                    for k in range(self.W):
                        prod.matrix[i][j]+=self.matrix[i][k]*other.matrix[k][j]
                        if self.mod:
                            prod.matrix[i][j]%=self.mod
        elif type(other)==int:
            assert self.H==self.W
            if other==0:
                return Matrix(H=self.H,eps=self.eps,mod=self.mod,identity=1)
            elif other==1:
                prod=Matrix(matrix=[[self.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
            else:
                prod=Matrix(H=self.H,eps=self.eps,mod=self.mod,identity=1)
                doub=self
                while other>=2:
                    if other&1:
                        prod@=doub
                    doub@=doub
                    other>>=1
                prod@=doub
        return prod

    def __itruediv__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    if self.mod:
                        self.matrix[i][j]=self.matrix[i][j]*MOD(self.mod).Pow(other.matrix[i][j],-1)%self.mod
                    else:
                        self.matrix[i][j]/=other.matrix[i][j]
        else:
            if self.mod:
                inve=MOD(self.mod).Pow(other,-1)
            for i in range(self.H):
                for j in range(self.W):
                    if self.mod:
                        self.matrix[i][j]=self.matrix[i][j]*inve%self.mod
                    else:
                        self.matrix[i][j]/=other
        return self

    def __ifloordiv__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]//=other.matrix[i][j]
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]//=other
        return self

    def __imod__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]%=other.matrix[i][j]
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]%=other
        return self

    def __ipow__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    if self.mod:
                        self.matrix[i][j]=pow(self.matrix[i][j],other.matrix[i][j],self.mod)
                    else:
                        self.matrix[i][j]=pow(self.matrix[i][j],other.matrix[i][j])
        else:
            for i in range(self.H):
                for j in range(self.W):
                    if self.mod:
                        self.matrix[i][j]=pow(self.matrix[i][j],other,self.mod)
                    else:
                        self.matrix[i][j]=pow(self.matrix[i][j],other)
        return self

    def __ilshift__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]<<=other.matrix[i][j]
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]<<=other
        return self

    def __irshift__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]>>=other.matrix[i][j]
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]>>=other
        return self

    def __iand__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]&=other.matrix[i][j]
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]&=other
        return self

    def __ior__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]|=other.matrix[i][j]
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]|=other
        return self

    def __ixor__(self,other):
        if type(other)==Matrix:
            assert self.H==other.H
            assert self.W==other.W
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]^=other.matrix[i][j]
        else:
            for i in range(self.H):
                for j in range(self.W):
                    self.matrix[i][j]^=other
        return self

    def __neg__(self):
        if self.mod:
            nega=Matrix(matrix=[[(-self.matrix[i][j])%self.mod for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        else:
            nega=Matrix(matrix=[[-self.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return nega

    def __pos__(self):
        posi=Matrix(matrix=[[self.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return posi

    def __invert__(self):
        inve=Matrix(matrix=[[~self.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return inve

    def __abs__(self):
        abso=Matrix(matrix=[[abs(self.matrix[i][j]) for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        return abso

    def __getitem__(self,i):
        if type(i)==int:
            return self.matrix[i]
        elif type(i)==tuple:
            i,j=i
            if type(i)==int:
                i=slice(i,i+1)
            if type(j)==int:
                j=slice(j,j+1)
            return Matrix(matrix=[lst[j] for lst in self.matrix[i]],eps=self.eps,mod=self.mod)

    def __contains__(self,x):
        for i in range(self.H):
            if x in self.matrix[i]:
                return True
        return False

    def __str__(self):
        digit=[max(len(str(self.matrix[i][j])) for i in range(self.H)) for j in range(self.W)]
        return "\n".join([(" [" if i else "[[")+", ".join([str(self.matrix[i][j]).rjust(digit[j]," ") for j in range(self.W)])+"]" for i in range(self.H)])+"]"

    def __bool__(self):
        return True

    def Transpose(self):
        return Matrix(matrix=[[self.matrix[i][j] for i in range(self.H)] for j in range(self.W)])

    def Trace(self):
        assert self.H==self.W
        trace=sum(self.matrix[i][i] for i in range(self.H))
        if self.mod:
            trace%=self.mod
        return trace

    def Elem_Raw_Operate_1(self,i0,i1):
        self.matrix[i0],self.matrix[i1]=self.matrix[i1],self.matrix[i0]

    def Elem_Raw_Operate_2(self,i,c):
        if self.mod:
            self.matrix[i]=[self.matrix[i][j]*c%self.mod for j in range(self.W)]
        else:
            self.matrix[i]=[self.matrix[i][j]*c for j in range(self.W)]

    def Elem_Raw_Operate_3(self,i0,i1,c):
        if self.mod:
            self.matrix[i0]=[(self.matrix[i0][j]+c*self.matrix[i1][j])%self.mod for j in range(self.W)]
        else:
            self.matrix[i0]=[self.matrix[i0][j]+c*self.matrix[i1][j] for j in range(self.W)]

    def Elimination(self,determinant=False,inverse_matrix=False,linear_equation=False,rank=False,upper_triangular=False):
        h=0
        ut=Matrix(matrix=[[self.matrix[i][j] for j in range(self.W)] for i in range(self.H)],eps=self.eps,mod=self.mod)
        if determinant or inverse_matrix:
            assert self.H==self.W
            det=1
        if inverse_matrix:
            assert self.H==self.W
            im=Matrix(H=self.H,eps=self.eps,mod=self.mod,identity=1)
        if linear_equation:
            assert self.H==linear_equation.H
            le=Matrix(matrix=[[linear_equation.matrix[i][j] for j in range(linear_equation.W)] for i in range(linear_equation.H)],eps=self.eps,mod=self.mod)
        for j in range(ut.W):
            for i in range(h,ut.H):
                if abs(ut.matrix[i][j])>ut.eps:
                    if determinant or inverse_matrix:
                        det*=ut.matrix[i][j]
                        if self.mod:
                            det%=self.mod
                    if self.mod:
                        inve=MOD(self.mod).Pow(ut.matrix[i][j],-1)
                    else:
                        inve=1/ut.matrix[i][j]

                    ut.Elem_Raw_Operate_1(i,h)
                    if determinant and i!=h and self.mod:
                        det=(-det)%self.mod
                    if inverse_matrix:
                        im.Elem_Raw_Operate_1(i,h)
                    if linear_equation:
                        le.Elem_Raw_Operate_1(i,h)

                    ut.Elem_Raw_Operate_2(h,inve)
                    if inverse_matrix:
                        im.Elem_Raw_Operate_2(h,inve)
                    if linear_equation:
                        le.Elem_Raw_Operate_2(h,inve)

                    for ii in range(ut.H):
                        if ii==h:
                            continue
                        x=-ut.matrix[ii][j]
                        ut.Elem_Raw_Operate_3(ii,h,x)
                        if inverse_matrix:
                            im.Elem_Raw_Operate_3(ii,h,x)
                        if linear_equation:
                            le.Elem_Raw_Operate_3(ii,h,x)
                    h+=1
                    break
            else:
                det=0
        if linear_equation and any(le[i][0] for i in range(h,self.H)):
            le=None
        tpl=()
        if determinant:
            tpl+=(det,)
        if inverse_matrix:
            if det==0:
                im=None
            tpl+=(im,)
        if linear_equation:
            tpl+=(le,)
        if rank:
            tpl+=(h,)
        if upper_triangular:
            tpl+=(ut,)
        if len(tpl)==1:
            tpl=tpl[0]
        return tpl

N=int(input())
mod=998244353
ans=N*Arithmetic_Sequence_Sum(N,1,10**len(str(N)),mod)%mod
print(ans)