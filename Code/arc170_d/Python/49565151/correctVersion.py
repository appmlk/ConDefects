import sys

if len(sys.argv) >= 2 and sys.argv[1] == "ONLINE_JUDGE":
    import os
    from cffi import FFI
    ffibuilder = FFI()
    current_directory = os.getcwd()
    
    ffibuilder.cdef("""
        typedef long long TYPE;
        void *multiset_new();
        void multiset_insert(void *obj, TYPE val);
        void multiset_erase(void *obj, TYPE val);
        bool multiset_contain(void *obj, TYPE val);
        TYPE multiset_get_max(void *obj);
        TYPE multiset_get_min(void *obj);
        int multiset_size(void *obj);
    """)
    
    ffibuilder.set_source("cpp_interop",
    """
        #include <set>
        extern "C" {
        using MULTISET=std::multiset<long long>;
        using TYPE=long long;
        void *multiset_new() { return new MULTISET(); }
        void multiset_insert(void *obj, TYPE val) { ((MULTISET*)obj)->insert(val); }
        void multiset_erase(void *obj, TYPE val) { ((MULTISET*)obj)->erase(((MULTISET*)obj)->find(val)); }
        bool multiset_contain(void *obj, TYPE val) { return ((MULTISET*)obj)->find(val) != ((MULTISET*)obj)->end(); }
        TYPE multiset_get_max(void *obj) { return *((MULTISET*)obj)->rbegin(); }
        TYPE multiset_get_min(void *obj) { return *((MULTISET*)obj)->begin(); }
        int multiset_size(void *obj) { return ((MULTISET*)obj)->size(); }
        }
    """,
        source_extension='.cpp',
        library_dirs=[current_directory],
        extra_link_args=['-Wl,-rpath=' + current_directory])
    
    if __name__ == "__main__":
        ffibuilder.compile(verbose=True, debug=True)
else:
    from cpp_interop import ffi, lib
    from collections import defaultdict
    class Multiset:
        def __init__(self):
            self.obj = lib.multiset_new()
        def insert(self, val):
            lib.multiset_insert(self.obj, val)
        def erase(self, val):
            lib.multiset_erase(self.obj, val)
        def contain(self, val):
            return lib.multiset_contain(self.obj, val)
        def get_max(self):
            return lib.multiset_get_max(self.obj)
        def get_min(self):
            return lib.multiset_get_min(self.obj)
        def size(self):
            return lib.multiset_size(self.obj)


    from bisect import bisect_left

    def solve():
        N = int(input())
        A = list(map(int,input().split()))
        B = list(map(int,input().split()))
        ms = Multiset()
        A.sort()
        B.sort()
        C = [0]*N
        D = [[0]*3 for i in range(N)]
        # righter = [[] for i in range(N)]
        lefter = [[] for i in range(N)]
        for i,b in enumerate(B):
            j = bisect_left(A, b)
            C[i] = j
            # if 0<=j<N:
            #     righter[j].append(i)
            if 0<=j-1<N:
                lefter[j-1].append(i)
            if 0<=j<N:
                right = A[j]
            else:
                right = 10**12
            if 0<=j-1<N:
                left = A[j-1]
            else:
                left = -10**12
            D[i][0] = min(right-b, b-left)
            if 0<=j+1<N:
                right1 = A[j+1]
            else:
                right1 = 10**12
            D[i][1] = min(right1-b, b-left)
            if 0<=j-2<N:
                left1 = A[j-2]
            else:
                left1 = -10**12
            D[i][2] = min(right-b, b-left1)
        for a,b,c in D:
            ms.insert(a)
        j = 0
        flag = False
        for i, a in enumerate(A):
            while j<N and B[j]<a:
                ms.erase(D[j][0])
                j+=1
            for k in lefter[i]:
                ms.erase(D[k][0])
                ms.insert(D[k][2])
            flag1 = False
            flag3 = False
            if ms.size() > 0:
                if a > ms.get_max():
                    flag1 = True
            else:
                flag1 = True
                flag3 = True
            flag2 = False
            flag4 = False
            if B[0] <= a:
                if i>0:
                    if A[i-1] > a-B[0]:
                        flag2 = True
                if i<N-1:
                    if A[i+1] < a+B[0]:
                        flag2 = True
            else:
                flag2 = True
                flag4 = True
            if flag1 and flag2:
                flag = True
                break
            for k in lefter[i]:
                ms.erase(D[k][2])
                ms.insert(D[k][0])
        if flag:
            print("Alice")
        else:
            print("Bob")
    T = int(input())

    for i in range(T):
        solve()