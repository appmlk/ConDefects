from sys import stdin


class FastIO:
    def __init__(self):
        self.random_seed = 0
        self.flush = False
        self.inf = 1 << 32
        return

    @staticmethod
    def read_int():
        return int(stdin.readline().rstrip())

    @staticmethod
    def read_float():
        return float(stdin.readline().rstrip())

    @staticmethod
    def read_list_ints():
        return list(map(int, stdin.readline().rstrip().split()))

    @staticmethod
    def read_list_ints_minus_one():
        return list(map(lambda x: int(x) - 1, stdin.readline().rstrip().split()))

    @staticmethod
    def read_str():
        return stdin.readline().rstrip()

    @staticmethod
    def read_list_strs():
        return stdin.readline().rstrip().split()

    def get_random_seed(self):
        import random
        self.random_seed = random.randint(0, 10 ** 9 + 7)
        return

    def st(self, x):
        return print(x, flush=self.flush)

    def lst(self, x):
        return print(*x, flush=self.flush)

    def flatten(self, lst):
        self.st("\n".join(str(x) for x in lst))
        return

    @staticmethod
    def max(a, b):
        return a if a > b else b

    @staticmethod
    def min(a, b):
        return a if a < b else b

    @staticmethod
    def ceil(a, b):
        return a // b + int(a % b != 0)

    @staticmethod
    def accumulate(nums):
        n = len(nums)
        pre = [0] * (n + 1)
        for i in range(n):
            pre[i + 1] = pre[i] + nums[i]
        return pre


class SuffixArray:

    def __init__(self):
        return

    @staticmethod
    def build(s, sig):
        # sa: index is rank and value is pos
        # rk: index if pos and value is rank
        # height: lcp of rank i-th suffix and (i-1)-th suffix
        # sum(height): count of same substring of s
        # n*(n+1)//2 - sum(height): count of different substring of s
        # max(height): can compute the longest duplicate substring,
        # which is s[i: i + height[j]] and j = height.index(max(height)) and i = sa[j]
        # sig: number of unique rankings which initially is the size of the character set

        n = len(s)
        sa = list(range(n))
        rk = s[:]
        ll = 0  # ll is the length that has already been sorted, and now it needs to be sorted by 2ll length
        tmp = [0] * n
        while True:
            p = [i for i in range(n - ll, n)] + [x - ll for i, x in enumerate(sa) if x >= ll]
            # for suffixes with a length less than l, their second keyword ranking is definitely
            # the smallest because they are all empty
            # for suffixes of other lengths, suffixes starting at 'sa [i]' rank i-th, and their
            # first ll characters happen to be the second keyword of suffixes starting at 'sa[i] - ll'
            # start cardinality sorting, and first perform statistics on the first keyword
            # first, count how many values each has
            cnt = [0] * sig
            for i in range(n):
                cnt[rk[i]] += 1
            # make a prefix and for easy cardinality sorting
            for i in range(1, sig):
                cnt[i] += cnt[i - 1]

            # then use cardinality sorting to calculate the new sa
            for i in range(n - 1, -1, -1):
                w = rk[p[i]]
                cnt[w] -= 1
                sa[cnt[w]] = p[i]

            # new_sa to check new_rk
            def equal(ii, jj, lll):
                if rk[ii] != rk[jj]:
                    return False
                if ii + lll >= n and jj + lll >= n:
                    return True
                if ii + lll < n and jj + lll < n:
                    return rk[ii + lll] == rk[jj + lll]
                return False

            sig = -1
            for i in range(n):
                tmp[i] = 0

            for i in range(n):
                # compute the lcp
                if i == 0 or not equal(sa[i], sa[i - 1], ll):
                    sig += 1
                tmp[sa[i]] = sig

            for i in range(n):
                rk[i] = tmp[i]
            sig += 1
            if sig == n:
                break
            ll = ll << 1 if ll > 0 else 1

        # height
        k = 0
        height = [0] * n
        for i in range(n):
            if rk[i] > 0:
                j = sa[rk[i] - 1]
                while i + k < n and j + k < n and s[i + k] == s[j + k]:
                    k += 1
                height[rk[i]] = k
                k = 0 if k - 1 < 0 else k - 1
        return sa, rk, height


class SortedList:
    def __init__(self, iterable=None, _load=200):
        """Initialize sorted list instance."""
        if iterable is None:
            iterable = []
        values = sorted(iterable)
        self._len = _len = len(values)
        self._load = _load
        self._lists = _lists = [values[i:i + _load]
                                for i in range(0, _len, _load)]
        self._list_lens = [len(_list) for _list in _lists]
        self._min_s = [_list[0] for _list in _lists]
        self._fen_tree = []
        self._rebuild = True

    def _fen_build(self):
        """Build a fenwick tree instance."""
        self._fen_tree[:] = self._list_lens
        _fen_tree = self._fen_tree
        for i in range(len(_fen_tree)):
            if i | i + 1 < len(_fen_tree):
                _fen_tree[i | i + 1] += _fen_tree[i]
        self._rebuild = False

    def _fen_update(self, index, value):
        """Update `fen_tree[index] += value`."""
        if not self._rebuild:
            _fen_tree = self._fen_tree
            while index < len(_fen_tree):
                _fen_tree[index] += value
                index |= index + 1

    def _fen_query(self, end):
        """Return `sum(_fen_tree[:end])`."""
        if self._rebuild:
            self._fen_build()

        _fen_tree = self._fen_tree
        x = 0
        while end:
            x += _fen_tree[end - 1]
            end &= end - 1
        return x

    def _fen_findkth(self, k):
        """Return a pair of (the largest `idx` such that `sum(_fen_tree[:idx]) <= k`, `k - sum(_fen_tree[:idx])`)."""
        _list_lens = self._list_lens
        if k < _list_lens[0]:
            return 0, k
        if k >= self._len - _list_lens[-1]:
            return len(_list_lens) - 1, k + _list_lens[-1] - self._len
        if self._rebuild:
            self._fen_build()

        _fen_tree = self._fen_tree
        idx = -1
        for d in reversed(range(len(_fen_tree).bit_length())):
            right_idx = idx + (1 << d)
            if right_idx < len(_fen_tree) and k >= _fen_tree[right_idx]:
                idx = right_idx
                k -= _fen_tree[idx]
        return idx + 1, k

    def _delete(self, pos, idx):
        """Delete value at the given `(pos, idx)`."""
        _lists = self._lists
        _mins = self._min_s
        _list_lens = self._list_lens

        self._len -= 1
        self._fen_update(pos, -1)
        del _lists[pos][idx]
        _list_lens[pos] -= 1

        if _list_lens[pos]:
            _mins[pos] = _lists[pos][0]
        else:
            del _lists[pos]
            del _list_lens[pos]
            del _mins[pos]
            self._rebuild = True

    def _loc_left(self, value):
        """Return an index pair that corresponds to the first position of `value` in the sorted list."""
        if not self._len:
            return 0, 0

        _lists = self._lists
        _mins = self._min_s

        lo, pos = -1, len(_lists) - 1
        while lo + 1 < pos:
            mi = (lo + pos) >> 1
            if value <= _mins[mi]:
                pos = mi
            else:
                lo = mi

        if pos and value <= _lists[pos - 1][-1]:
            pos -= 1

        _list = _lists[pos]
        lo, idx = -1, len(_list)
        while lo + 1 < idx:
            mi = (lo + idx) >> 1
            if value <= _list[mi]:
                idx = mi
            else:
                lo = mi

        return pos, idx

    def _loc_right(self, value):
        """Return an index pair that corresponds to the last position of `value` in the sorted list."""
        if not self._len:
            return 0, 0

        _lists = self._lists
        _mins = self._min_s

        pos, hi = 0, len(_lists)
        while pos + 1 < hi:
            mi = (pos + hi) >> 1
            if value < _mins[mi]:
                hi = mi
            else:
                pos = mi

        _list = _lists[pos]
        lo, idx = -1, len(_list)
        while lo + 1 < idx:
            mi = (lo + idx) >> 1
            if value < _list[mi]:
                idx = mi
            else:
                lo = mi

        return pos, idx

    def add(self, value):
        """Add `value` to sorted list."""
        _load = self._load
        _lists = self._lists
        _mins = self._min_s
        _list_lens = self._list_lens

        self._len += 1
        if _lists:
            pos, idx = self._loc_right(value)
            self._fen_update(pos, 1)
            _list = _lists[pos]
            _list.insert(idx, value)
            _list_lens[pos] += 1
            _mins[pos] = _list[0]
            if _load + _load < len(_list):
                _lists.insert(pos + 1, _list[_load:])
                _list_lens.insert(pos + 1, len(_list) - _load)
                _mins.insert(pos + 1, _list[_load])
                _list_lens[pos] = _load
                del _list[_load:]
                self._rebuild = True
        else:
            _lists.append([value])
            _mins.append(value)
            _list_lens.append(1)
            self._rebuild = True

    def discard(self, value):
        """Remove `value` from sorted list if it is a member."""
        _lists = self._lists
        if _lists:
            pos, idx = self._loc_right(value)
            if idx and _lists[pos][idx - 1] == value:
                self._delete(pos, idx - 1)

    def remove(self, value):
        """Remove `value` from sorted list; `value` must be a member."""
        _len = self._len
        self.discard(value)
        if _len == self._len:
            raise ValueError('{0!r} not in list'.format(value))

    def pop(self, index=-1):
        """Remove and return value at `index` in sorted list."""
        pos, idx = self._fen_findkth(self._len + index if index < 0 else index)
        value = self._lists[pos][idx]
        self._delete(pos, idx)
        return value

    def bisect_left(self, value):
        """Return the first index to insert `value` in the sorted list."""
        pos, idx = self._loc_left(value)
        return self._fen_query(pos) + idx

    def bisect_right(self, value):
        """Return the last index to insert `value` in the sorted list."""
        pos, idx = self._loc_right(value)
        return self._fen_query(pos) + idx

    def count(self, value):
        """Return number of occurrences of `value` in the sorted list."""
        return self.bisect_right(value) - self.bisect_left(value)

    def __len__(self):
        """Return the size of the sorted list."""
        return self._len

    def __getitem__(self, index):
        """Lookup value at `index` in sorted list."""
        pos, idx = self._fen_findkth(self._len + index if index < 0 else index)
        return self._lists[pos][idx]

    def __delitem__(self, index):
        """Remove value at `index` from sorted list."""
        pos, idx = self._fen_findkth(self._len + index if index < 0 else index)
        self._delete(pos, idx)

    def __contains__(self, value):
        """Return true if `value` is an element of the sorted list."""
        _lists = self._lists
        if _lists:
            pos, idx = self._loc_left(value)
            return idx < len(_lists[pos]) and _lists[pos][idx] == value
        return False

    def __iter__(self):
        """Return an iterator over the sorted list."""
        return (value for _list in self._lists for value in _list)

    def __reversed__(self):
        """Return a reverse iterator over the sorted list."""
        return (value for _list in reversed(self._lists)
                for value in reversed(_list))

    def __repr__(self):
        """Return strings representation of sorted list."""
        return 'SortedList({0})'.format(list(self))


class Solution:
    def __init__(self):
        return

    @staticmethod
    def main(ac=FastIO()):
        """
        url: url of the problem
        tag: algorithm tag
        """
        n = ac.read_int()
        s = [ord(w) - ord("a") for w in ac.read_str()]
        t = [ord(w) - ord("a") for w in ac.read_str()]
        lst = s + s + t + t
        sa, rk, height = SuffixArray().build(lst, 26)
        ans = 0
        ind = SortedList(list(range(2 * n, 3 * n)))
        j = 0
        for i in range(4 * n):
            while j < i and height[i] < n:
                if 2 * n <= sa[j] < 3 * n:
                    ind.discard(sa[j])
                j += 1
            if 0 <= sa[i] < n:
                ans += len(ind)
        ac.st(ans)
        return


Solution().main()
