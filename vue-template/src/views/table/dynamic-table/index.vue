<template>
  <div class="app-container">
    <unfixed-thead />
  </div>
</template>

<script>
import FixedThead from './components/FixedThead';
import UnfixedThead from './components/UnfixedThead';

export default {
  name: 'DynamicTable',
  components: { FixedThead, UnfixedThead },
};
</script>
